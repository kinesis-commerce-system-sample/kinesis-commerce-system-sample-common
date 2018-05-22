package com.example.kinesiscommercesystemsample.common.web.base.aop;

import com.eaio.uuid.UUID;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.slf4j.MDC;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static java.util.concurrent.TimeUnit.NANOSECONDS;

@Slf4j
public class RequestTrackingHandlerInterceptor extends BaseHandlerInterceptor {


	private static final String REQUEST_ID = "Request-ID";

	private static final String HEADER_X_TRACK_ID = "X-Track-Id";

	private static final String ELAPSED = "Elapsed";

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

		// リクエストID
		val requestId = new UUID().toString();

		// 現在時刻を記録
		val beforeNanoSec = System.nanoTime();

		// トラッキングID
		val trackId = getTrackId(request);

		// スレッドローカルなリクエスト情報保持クラスにセット
		RequestTrackinginfoHolder.set(requestId, beforeNanoSec, trackId);

		// リクエストIDをMDCにセット
		MDC.put(REQUEST_ID, RequestTrackinginfoHolder.getRequestId());

		// トラッキングIDをMDCにセット
		MDC.put(HEADER_X_TRACK_ID, RequestTrackinginfoHolder.getTrackId());
		// トラッキングIDをレスポンスヘッダーにセット
		response.setHeader(HEADER_X_TRACK_ID, RequestTrackinginfoHolder.getTrackId());

		log.info("{} {}", request.getMethod(), request.getRequestURI());

		return true;
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

		val beforeNanoSec = RequestTrackinginfoHolder.getStartTime();
		val elapsedNanoSec = System.nanoTime() - beforeNanoSec;
		val elapsedMilliSec = NANOSECONDS.toMillis(elapsedNanoSec);

		// 処理時間をMDCにセット
		MDC.put(ELAPSED, String.valueOf(elapsedMilliSec));

		log.info("{} {}, Elapsed {}ms.", request.getMethod(), request.getRequestURI(), elapsedMilliSec);

		// 処理時間をMDCから削除
		MDC.remove(ELAPSED);

		// スレッドローカルに保持しているリクエスト情報を削除
		RequestTrackinginfoHolder.clear();

		// リクエストIDをMDCから削除
		MDC.remove(REQUEST_ID);

		// トラッキングIDをMDCから削除
		MDC.remove(HEADER_X_TRACK_ID);
	}

	/**
	 * トラッキングIDを取得する。
	 *
	 * @param request
	 * @return
	 */
	private String getTrackId(HttpServletRequest request) {
		String trackId = request.getHeader(HEADER_X_TRACK_ID);
		if (trackId == null) {

			trackId = "UNDEFINED";
		}

		return trackId;
	}

	@Data
	public static class RequestTrackinginfoHolder {

		private static final ThreadLocal<String> REQUEST_ID = new ThreadLocal<>();

		private static final ThreadLocal<Long> START_TIME = new ThreadLocal<>();

		private static final ThreadLocal<String> TRACK_ID = new ThreadLocal<>();


		private static void set(String requestId, Long startTime, String trackId) {
			REQUEST_ID.set(requestId);
			START_TIME.set(startTime);
			TRACK_ID.set(trackId);
		}

		private static void clear() {
			REQUEST_ID.remove();
			START_TIME.remove();
			TRACK_ID.remove();
		}

		public static String getRequestId() {
			return REQUEST_ID.get();
		}

		public static Long getStartTime() {
			return START_TIME.get();
		}

		public static String getTrackId() {
			return TRACK_ID.get();
		}
	}
}
