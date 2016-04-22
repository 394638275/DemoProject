package com.lew.mapleleaf.utils.common;

public class JsonUtils {

//	public static int parseErrorCode(String data) {
//		if (TextUtils.isEmpty(data)) {
//			return ErrorCodeUtils.UNKNOW_ERROR;
//		}
//		try {
//			JsonObject joRetValue = new JsonParser().parse(data).getAsJsonObject();
//			int errorCode = ErrorCodeUtils.UNKNOW_ERROR;
//			if (joRetValue.has("errorCode")) {
//				errorCode = joRetValue.get("errorCode").getAsInt();
//			}
//			return errorCode;
//		} catch (JsonSyntaxException e) {
//			e.printStackTrace();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return ErrorCodeUtils.UNKNOW_ERROR;
//	}
//
//	public static String parseMsg(String data) {
//		String retValue = "";
//		if (!TextUtils.isEmpty(data)) {
//			try {
//				JsonObject joRetValue = new JsonParser().parse(data).getAsJsonObject();
//				if (joRetValue.has("msg")) {
//					retValue = joRetValue.get("msg").getAsString();
//				}else{
//					retValue = ZJHApplication.getInstance().getString(R.string.data_format_error);
//				}
//			} catch (JsonSyntaxException e) {
//				e.printStackTrace();
//				retValue = ZJHApplication.getInstance().getString(R.string.data_format_error);
//			} catch (Exception e) {
//				e.printStackTrace();
//				retValue = ZJHApplication.getInstance().getString(R.string.unkown_error);
//			}
//		}else{
//			retValue = ZJHApplication.getInstance().getString(R.string.unkown_error);
//		}
//		return retValue;
//	}
}
