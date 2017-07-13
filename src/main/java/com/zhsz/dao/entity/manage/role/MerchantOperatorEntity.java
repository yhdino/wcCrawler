package com.zhsz.dao.entity.manage.role;


import java.io.Serializable;
import java.util.Date;

/**
 * 操作员实体
 */
public class MerchantOperatorEntity implements Serializable {
		private String OPERATOR_ID;
		public String getOPERATOR_ID() {
			return OPERATOR_ID;
		}
		public void setOPERATOR_ID(String oPERATOR_ID) {
			OPERATOR_ID = oPERATOR_ID;
		}
		public String getPASSWORD() {
			return PASSWORD;
		}
		public void setPASSWORD(String pASSWORD) {
			PASSWORD = pASSWORD;
		}
		public String getNAME() {
			return NAME;
		}
		public void setNAME(String nAME) {
			NAME = nAME;
		}
		public String getDPMC() {
			return DPMC;
		}
		public void setDPMC(String dPMC) {
			DPMC = dPMC;
		}
		public String getSTORE_ID() {
			return STORE_ID;
		}
		public void setSTORE_ID(String sTORE_ID) {
			STORE_ID = sTORE_ID;
		}
		public String getMOBILE_NUM() {
			return MOBILE_NUM;
		}
		public void setMOBILE_NUM(String mOBILE_NUM) {
			MOBILE_NUM = mOBILE_NUM;
		}
		public String getTELE_NUM() {
			return TELE_NUM;
		}
		public void setTELE_NUM(String tELE_NUM) {
			TELE_NUM = tELE_NUM;
		}
		public String getEMAIL() {
			return EMAIL;
		}
		public void setEMAIL(String eMAIL) {
			EMAIL = eMAIL;
		}
		public String getSTATUS() {
			return STATUS;
		}
		public void setSTATUS(String sTATUS) {
			STATUS = sTATUS;
		}
		public String getJOB_NAME() {
			return JOB_NAME;
		}
		public void setJOB_NAME(String jOB_NAME) {
			JOB_NAME = jOB_NAME;
		}
		public String getREMARK() {
			return REMARK;
		}
		public void setREMARK(String rEMARK) {
			REMARK = rEMARK;
		}
		public String getLAST_IP() {
			return LAST_IP;
		}
		public void setLAST_IP(String lAST_IP) {
			LAST_IP = lAST_IP;
		}
		public Date getLAST_TIME() {
			return LAST_TIME;
		}
		public void setLAST_TIME(Date lAST_TIME) {
			LAST_TIME = lAST_TIME;
		}
		private String PASSWORD;
		private String NAME;
		private String DPMC;
		private String STORE_ID;
		private String MOBILE_NUM;
		private String TELE_NUM;
		private String EMAIL;
		private String STATUS;
		private String JOB_NAME;
		private String REMARK;
		private String LAST_IP;
		private Date LAST_TIME;
}
