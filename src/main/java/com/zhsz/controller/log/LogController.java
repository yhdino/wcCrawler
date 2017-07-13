package com.zhsz.controller.log;

import com.zhsz.dao.entity.manage.log.LogEntity;
import com.zhsz.service.manage.log.ILogService;
import com.zhsz.utils.exception.ServiceException;
import com.zhsz.utils.exception.WebException;
import com.zhsz.utils.logger.ZhszLogger;
import com.zhsz.utils.persistent.ResultList;
import com.zhsz.utils.utils.FileUtil;
import com.zhsz.utils.utils.StringUtils;
import com.zhsz.utils.utils.page.Pagination;
import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Colour;
import jxl.format.UnderlineStyle;
import jxl.write.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

@Controller
@RequestMapping("/log")
public class LogController {

	@Autowired
	private ILogService logService;

	/***
	 * 根据ID查详细
	 * 
	 * @param id ID
	 * @param model 模型
	 * @return 返回
	 * @throws WebException 异常
	 */
	@RequestMapping("getlogbyid")
	public String getLogById(String id, ModelMap model) throws WebException {
		try {
			LogEntity log = logService.getLogById(id);
			if (log != null) {
				model.addAttribute("log", log);
			}
		} catch (ServiceException e) {
			throw new ServiceException(e);
		}
		return "page/main/log/loginfo";
	}

	/***
	 * 添加日志
	 * 
	 * @return 返回
	 * @throws WebException 异常
	 */
	@RequestMapping("addlogs")
	public String getLogById() throws WebException {
		try {
			for (int i = 0; i < 5000; i++) {
				LogEntity log = new LogEntity();
				log.setId(StringUtils.getUUID());
				log.setOperatorId("admin");
				log.setOperatorName("管理员");
				log.setTime(new Date());
				log.setContent("测试500000");
				log.setIp("192.168.18.119");
				logService.addLog(log);
			}
		} catch (ServiceException e) {
			throw new ServiceException(e);
		}
		return "page/main/log/logList";
	}

	/**
	 * 列表查找
	 * 
	 * @param operatorId 操作ID
	 * @param operatorName 操作姓名
	 * @param beginTime 开始时间
	 * @param endTime 结束时间
	 * @param request 请求
	 * @param response 回应
	 * @param model 模型
	 * @param page 分页
	 * @return 返回
	 * @throws WebException Web异常
	 */
	@RequestMapping("querylogs")
	public String queryLogs(String operatorId, String operatorName,
			String beginTime, String endTime, HttpServletRequest request,
			HttpServletResponse response, ModelMap model, Pagination page)
			throws WebException {
		try {
			ResultList<LogEntity> log = logService.getLogs(operatorId,
					operatorName, beginTime, endTime, page);
			model.addAttribute("log", log);
			model.addAttribute("operatorId", operatorId);
			model.addAttribute("operatorName", operatorName);
			model.addAttribute("beginTime", beginTime);
			model.addAttribute("endTime", endTime);
		} catch (ServiceException e) {
			ZhszLogger.error("查询日志信息失败！异常：" + e.getMessage(), this.getClass());
			throw new WebException("查询日志信息失败！异常：" + e);
		}
		return "page/main/log/logList";
	}
	
	/**
     * 导出系统日志到excel文件
     * @param
     * @param response
     * @return
     * @throws WebException
     */
    @RequestMapping("exportexcel")
    public String exportExcel(String operatorId, HttpServletResponse response)
            throws WebException {
        try {
            Date date = new Date(System.currentTimeMillis());
            SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");

            // 创建excel文件 begin
            String path;
            path = "/userRecord/";
            File serialNumberFile = new File(path);
            serialNumberFile.mkdirs();
            serialNumberFile = File.createTempFile(f.format(date)
                    + "_userRecord_", ".xls", serialNumberFile);
            WritableWorkbook serialNumberWwb = Workbook
                    .createWorkbook(serialNumberFile);
            WritableSheet serialNumberWs = serialNumberWwb.createSheet("系统操作日志",
                    0);
            WritableFont font = new WritableFont(WritableFont.ARIAL, 12,
                    WritableFont.BOLD, false, UnderlineStyle.NO_UNDERLINE,
                    Colour.BLACK);
            WritableCellFormat cFormat = new WritableCellFormat(font);
            cFormat.setAlignment(Alignment.CENTRE);
            String[] serialNumberTitles = {"登录账号", "姓名", "时间", "IP地址", "内容"};
            Label serialNumberLabel;
            for (int i = 0; i < serialNumberTitles.length; i++) {
                serialNumberLabel = new Label(i, 0, serialNumberTitles[i],
                        cFormat);
                serialNumberWs.addCell(serialNumberLabel);
            }

            ResultList<LogEntity> list = logService.getLogsExcel(operatorId,"","","");

            try {
                insertExcelLogEntity(serialNumberWs, list.getResults());
            } catch (Exception e) {
            	ZhszLogger.error("导出数据错误", e, this.getClass());
                throw new WebException("导出数据错误，请稍后重试...");
            }
            serialNumberWwb.write();
            serialNumberWwb.close();
            response.setContentType("application/-excel");
            response.addHeader("Content-Disposition", "attachment; filename=\""
                    + serialNumberFile.getName() + "\"");
            response.getOutputStream().write(
                    FileUtil.readByte(serialNumberFile));
            serialNumberFile.delete();
        } catch (Exception e) {
        	ZhszLogger.error("导出数据错误", e, this.getClass());
            throw new WebException("导出数据错误，请稍后重试...");
        }
        
        return null;
    }

    /**
     * 将系统日志记录插入到excel文件中
     * @param sheet
     * @throws Exception
     */
    private void insertExcelLogEntity(WritableSheet sheet,
            List<LogEntity> logs) throws Exception {
        int row = 1;
        for (Iterator<LogEntity> iterator = logs.iterator(); iterator
                .hasNext();) {
            LogEntity log = iterator.next();
            if (log == null) {
                continue;
            }
            Label label;
            int index = 0;
            
            label = new Label(index++, row, log.getOperatorId());// 登录账号
            try {
                sheet.addCell(label);
            } catch (Exception e) {
            	ZhszLogger.error("导出数据错误", e, this.getClass());
                throw new WebException("导出数据错误，请稍后重试...");
            }
            
            label = new Label(index++, row, log.getOperatorName());// 姓名
            try {
                sheet.addCell(label);
            } catch (Exception e) {
            	ZhszLogger.error("导出数据错误", e, this.getClass());
                throw new WebException("导出数据错误，请稍后重试...");
            }
            
            label = new Label(index++, row, new SimpleDateFormat("yyyy-MM-dd HH-mm-ss").format(log.getTime()));// 时间
            try {
                sheet.addCell(label);
            } catch (Exception e) {
            	ZhszLogger.error("导出数据错误", e, this.getClass());
                throw new WebException("导出数据错误，请稍后重试...");
            }
            label = new Label(index++, row, log.getIp());// IP地址
            try {
                sheet.addCell(label);
            } catch (Exception e) {
            	ZhszLogger.error("导出数据错误", e, this.getClass());
                throw new WebException("导出数据错误，请稍后重试...");
            }
            
            label = new Label(index++, row, log.getContent());// 内容
            try {
                sheet.addCell(label);
            } catch (Exception e) {
            	ZhszLogger.error("导出数据错误", e, this.getClass());
                throw new WebException("导出数据错误，请稍后重试...");
            }
            row++;
        }
    }
}
