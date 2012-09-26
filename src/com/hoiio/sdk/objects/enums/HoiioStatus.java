package com.hoiio.sdk.objects.enums;

/*
Copyright (C) 2012 Hoiio Pte Ltd (http://www.hoiio.com)

Permission is hereby granted, free of charge, to any person
obtaining a copy of this software and associated documentation
files (the "Software"), to deal in the Software without
restriction, including without limitation the rights to use,
copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the
Software is furnished to do so, subject to the following
conditions:

The above copyright notice and this permission notice shall be
included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES
OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT
HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY,
WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING
FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR
OTHER DEALINGS IN THE SOFTWARE.
*/

import java.util.HashMap;
import java.util.Map;

public enum HoiioStatus {
	
	// Success case
	SUCCESS_OK("success_ok"),
	
	// Errors
	ERROR_ADD_NOTIFICATION_FAILED("error_add_notification_failed"),
	ERROR_AUTO_EXTEND_NOT_AVAILABLE("error_auto_extend_not_available"),
	ERROR_CALLER_ID_INVALID("error_caller_id_invalid"),
	ERROR_CALL_SERVICE_NOT_AVAILABLE("error_call_service_not_available"),
	ERROR_CONCURRENT_CALL_LIMIT_REACHED("error_concurrent_call_limit_reached"),
	ERROR_DEST1_INVALID("error_dest1_invalid"),
	ERROR_DEST1_INVALID_NUMBER_ID("error_dest1_invalid_number_id"),
	ERROR_DEST1_NOT_SUPPORTED("error_dest1_not_supported"),
	ERROR_DEST2_INVALID("error_dest2_invalid"),
	ERROR_DEST2_INVALID_NUMBER_ID("error_dest2_invalid_number_id"),
	ERROR_DEST2_NOT_SUPPORTED("error_dest2_not_supported"),
	ERROR_DEST_INVALID("error_dest_invalid"),
	ERROR_DESTINATION_NOT_SUPPORTED("error_destination_not_supported"),
	ERROR_DEST_INCOMING_BOTH_MISSING("error_dest_incoming_both_missing"),
	ERROR_DEST_INCOMING_BOTH_PRESENT("error_dest_incoming_both_present"),
	ERROR_DEST_INVALID_NUMBER_ID("error_dest_invalid_number_id"),
	ERROR_DEST_NOT_SUPPORTED("error_dest_not_supported"),
	ERROR_DEST_TOO_MANY("error_dest_too_many"),
	ERROR_DEVELOPER_SUSPENDED("error_developer_suspended"),
	ERROR_EXCEED_MAX_SUBSCRIPTIONS("error_exceed_max_subscriptions"),
	ERROR_FILE_INVALID("error_file_invalid"),
	ERROR_FILE_TOO_BIG("error_file_too_big"),
	ERROR_FORWARD_TO_INVALID("error_forward_to_invalid"),
	ERROR_FROM_INVALID("error_from_invalid"),
	ERROR_INCOMING_SMS_NOT_SUPPORTED("error_incoming_sms_not_supported"),
	ERROR_INSUFFICIENT_CREDIT("error_insufficient_credit"),
	ERROR_INVALID_ACCESS_TOKEN("error_invalid_access_token"),
	ERROR_INVALID_APP_ID("error_invalid_app_id"),
	ERROR_INVALID_ATTEMPTS("error_invalid_attempts"),
	ERROR_INVALID_DESTINATION("error_invalid_destination"),
	ERROR_INVALID_DURATION("error_invalid_duration"),
	ERROR_INVALID_MAX_DIGITS("error_invalid_max_digits"),
	ERROR_INVALID_MAX_DURATION("error_invalid_max_duration"),
	ERROR_INVALID_NOTIFY_URL("error_invalid_notify_url"),
	ERROR_PAGE_INVALID("error_page_invalid"),
	ERROR_INVALID_ROOM("error_invalid_room"),
	ERROR_INVALID_SENDER_NAME("error_invalid_sender_name"),
	ERROR_INVALID_SESSION("error_invalid_session"),
	ERROR_INVALID_TIMEOUT("error_invalid_timeout"),
	ERROR_INVALID_TXN_REF("error_invalid_txn_ref"),
	ERROR_INVALID_TYPE("error_invalid_type"),
	ERROR_MALFORMED_PARAMS("error_malformed_params"),
	ERROR_MAX_DURATION_INVALID("error_max_duration_invalid"),
	ERROR_MSG_EMPTY("error_msg_empty"),
	ERROR_MSG_TOO_BIG("error_msg_too_big"),
	ERROR_MSG_TOO_LONG("error_msg_too_big"),
	ERROR_MODE_INVALID("error_mode_invalid"),
	ERROR_MONITOR_IN_PROGRESS("error_monitor_in_progress"),
	ERROR_MSG_CANNOT_CONVERT_TEXT("error_msg_cannot_convert_text"),
	ERROR_MSG_DOWNLOAD_FAILED("error_msg_download_failed"),
	ERROR_MSG_INVALID_FILE_FORMAT("error_msg_invalid_file_format"),
	ERROR_MSG_INVALID_FILE_SIZE("error_msg_invalid_file_size"),
	ERROR_MSG_INVALID_GENDER("error_msg_invalid_gender"),
	ERROR_MSG_INVALID_LANGUAGE("error_msg_invalid_language"),
	ERROR_MSG_INVALID_URL("error_msg_invalid_url"),
	ERROR_MSG_INVALID_XML("error_msg_invalid_xml"),
	ERROR_NOT_ALLOWED_FOR_TRIAL("error_not_allowed_for_trial"),
	ERROR_NO_NUMBER_AVAILABLE("error_no_number_available"),
	ERROR_NUMBER_INVALID("error_number_invalid"),
	ERROR_NUMBER_NOT_ASSIGNED("error_number_not_assigned"),
	ERROR_NUMBER_NOT_AVAILABLE("error_number_not_available"),
	ERROR_ON_FAILURE_INVALID("error_on_failure_invalid"),
	ERROR_PAGE_SIZE_INVALID("error_page_size_invalid"),
	ERROR_PERMISSION_DENIED("error_permission_denied"),
	ERROR_PICK_NUMBER_NOT_AVAILABLE("error_pick_number_not_available"),
	ERROR_SAME_DEST1_DEST2("error_same_dest1_dest2"),
	ERROR_SERVICE_NOT_AVAILABLE("error_service_not_available"),
	ERROR_SESSION_ACCESS_DENIED("error_session_access_denied"),
	ERROR_SESSION_ENDED("error_session_ended"),
	ERROR_SESSION_NOT_READY("error_session_not_ready"),
	ERROR_SMS_REBRAND_NOT_ENABLED("error_sms_rebrand_not_enabled"),
	ERROR_SMS_SERVICE_NOT_AVAILABLE("error_sms_service_not_available"),
	ERROR_SUBSCRIBE_NUMBER_NOT_AVAILABLE("error_subscribe_number_not_available"),
	ERROR_TO_BEFORE_FROM("error_to_before_from"),
	ERROR_TO_INVALID_FORMAT("error_to_invalid"),
	ERROR_TXN_REF_ACCESS_DENIED("error_txn_ref_access_denied"),
	ERROR_TXN_REF_INVALID("error_txn_ref_invalid"),
	ERROR_UNABLE_TO_RESOLVE_FORWARD_TO("error_unable_to_resolve_forward_to"),
	ERROR_UNABLE_TO_RESOLVE_NOTIFY_URL("error_unable_to_resolve_notify_url"),
	ERROR_USER_NOT_FOUND("error_user_not_found"),
	ERROR_USER_NOT_LIVE("error_user_not_live"),
	ERROR_CONNECT_NOT_ENABLED("error_connect_not_enabled"),
	ERROR_APPLICATION_NOT_PUBLISHED("error_application_not_published"),
	ERROR_CODE_EXPIRED("error_code_expired"),
	ERROR_INVALID_CODE("error_invalid_code"),
	
	// Params missing
	ERROR_ACCESS_TOKEN_PARAM_MISSING("error_access_token_param_missing"),
	ERROR_APP_ID_PARAM_MISSING("error_app_id_param_missing"),
	ERROR_COUNTRY_PARAM_MISSING("error_country_param_missing"),
	ERROR_DEST1_PARAM_MISSING("error_dest1_param_missing"),
	ERROR_DEST2_PARAM_MISSING("error_dest2_param_missing"),
	ERROR_DEST_PARAM_MISSING("error_dest_param_missing"),
	ERROR_DURATION_PARAM_MISSING("error_duration_param_missing"),
	ERROR_FILE_PARAM_MISSING("error_file_param_missing"),
	ERROR_INCOMING_PARAM_MISSING("error_incoming_param_missing"),
	ERROR_MSG_PARAM_MISSING("error_msg_param_missing"),
	ERROR_NOTIFY_URL_PARAM_MISSING("error_notify_url_param_missing"),
	ERROR_SESSION_PARAM_MISSING("error_session_param_missing"),
	ERROR_STATE_PARAM_MISSING("error_state_param_missing"),
	ERROR_TXN_REF_PARAM_MISSING("error_txn_ref_param_missing"),
	
	// Internal Server Error
	ERROR_INTERNAL_SERVER_ERROR("error_internal_server_error");
	
	private static final Map<String, HoiioStatus> lookup = new HashMap<String, HoiioStatus>();
	
	private String status;
	
	private HoiioStatus(String status) {
		this.status = status;
	}
	
	/**
	 * Returns a string representation of the object
	 * @return string representation of the object
	 */
	public String toString() {
		return status;
	}
	
	static {
		for (HoiioStatus s : HoiioStatus.values()) {
			lookup.put(s.toString(), s);
		}
	}

	/**
	 * Converts the string to {@code HoiioStatus} object
	 * @param status The hoiio status in string
	 * @return {@code HoiioStatus} object
	 */
	public static HoiioStatus fromString(String status) {
		return lookup.get(status);
	}
}
