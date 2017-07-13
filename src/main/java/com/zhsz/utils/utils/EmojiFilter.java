package com.zhsz.utils.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmojiFilter {
	/**
	 * 检测是否有emoji字符
	 * 
	 * @param source
	 * @return 一旦含有就抛出
	 */
	public static boolean containsEmoji(String source) {
		if (StringUtils.isBlank(source)) {
			return false;
		}

		int len = source.length();

		for (int i = 0; i < len; i++) {
			char codePoint = source.charAt(i);

			if (isEmojiCharacter(codePoint)) {
				// do nothing，判断到了这里表明，确认有表情字符
				return true;
			}
		}

		return false;
	}

	private static boolean isEmojiCharacter(char codePoint) {
		return (codePoint == 0x0) || (codePoint == 0x9) || (codePoint == 0xA)
				|| (codePoint == 0xD)
				|| ((codePoint >= 0x20) && (codePoint <= 0xD7FF))
				|| ((codePoint >= 0xE000) && (codePoint <= 0xFFFD))
				|| ((codePoint >= 0x10000) && (codePoint <= 0x10FFFF));
	}

	/**
	 * 过滤emoji 或者 其他非文字类型的字符
	 * 
	 * @param source
	 * @return
	 */
	public static String filterEmoji(String source) {

		if (!containsEmoji(source)) {
			return source;// 如果不包含，直接返回
		}
		// 到这里铁定包含
		StringBuilder buf = null;

		int len = source.length();

		for (int i = 0; i < len; i++) {
			char codePoint = source.charAt(i);

			if (isEmojiCharacter(codePoint)) {
				if (buf == null) {
					buf = new StringBuilder(source.length());
				}

				buf.append(codePoint);
			} else {
			}
		}

		if (buf == null) {
			return source;// 如果没有找到 emoji表情，则返回源字符串
		} else {
			if (buf.length() == len) {// 这里的意义在于尽可能少的toString，因为会重新生成字符串
				buf = null;
				return source;
			} else {
				return buf.toString();
			}
		}

	}
	/**
     * 将str中的emoji表情转为byte数组
     *
     * @param str
     * @return
     */
	 public static String resolveToByteFromEmoji(String str) {
        Pattern pattern = Pattern
                .compile("[^(\u2E80-\u9FFF\\w\\s`~!@#\\$%\\^&\\*\\(\\)_+-？（）——=\\[\\]{}\\|;。，、《》”：；“！……’:'\"<,>\\.?/\\\\*)]");
        Matcher matcher = pattern.matcher(str);
        StringBuffer sb2 = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(sb2, resolveToByte(matcher.group(0)));
        }
        matcher.appendTail(sb2);
        return sb2.toString();
    }
	 
	  
	 private static String resolveToByte(String str) {
        byte[] b = str.getBytes();
        StringBuffer sb = new StringBuffer();
        sb.append("<:");
        for (int i = 0; i < b.length; i++) {
            if (i < b.length - 1) {
                sb.append(Byte.valueOf(b[i]).toString() + ",");
            } else {
                sb.append(Byte.valueOf(b[i]).toString());
            }
        }
        sb.append(":>");
        return sb.toString();
	 }
	 
	 /**
     * 将str中的byte数组类型的emoji表情转为正常显示的emoji表情
     */
	 public static String resolveToEmojiFromByte(String str) {
        Pattern pattern2 = Pattern.compile("<:([[-]\\d*[,]]+):>");
        Matcher matcher2 = pattern2.matcher(str);
        StringBuffer sb3 = new StringBuffer();
        while (matcher2.find()) {
            matcher2.appendReplacement(sb3, resolveToEmoji(matcher2.group(0)));
        }
        matcher2.appendTail(sb3);
        return sb3.toString();
	}
	 
	 private static String resolveToEmoji(String str) {
        str = str.replaceAll("<:", "").replaceAll(":>", "");
        String[] s = str.split(",");
        byte[] b = new byte[s.length];
        for (int i = 0; i < s.length; i++) {
            b[i] = Byte.valueOf(s[i]);
        }
        return new String(b);
    }
}
