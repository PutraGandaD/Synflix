package com.putragandad.moviedbch5.utils

class Constant {
    companion object {
        const val BASE_URL = "https://api.themoviedb.org/3/"
        const val API_KEY = "eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJhMjMwYWQxZTMzYWVlY2E4YTczODRiY2I5YzFiNWNiMCIsInN1YiI6IjY2M2I3NGExMDhiYTI1MWYxODA1YWM0OSIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.TyTdJl2bM7g4DkeOmQk-CkphsfHuxJOk6_V6qtcpmic"

        const val MOVIES_ID_EXTRA = "MOVIES_ID_EXTRA"

        // Notification Channel constants

        // Name of Notification Channel for verbose notifications of background work
        @JvmField val VERBOSE_NOTIFICATION_CHANNEL_NAME: CharSequence =
            "Verbose WorkManager Notifications"
        const val VERBOSE_NOTIFICATION_CHANNEL_DESCRIPTION =
            "Shows notifications whenever work starts"
        @JvmField val NOTIFICATION_TITLE: CharSequence = "Synflix"
        const val CHANNEL_ID = "VERBOSE_NOTIFICATION"
        const val NOTIFICATION_ID = 1

        // The name of the image manipulation work
        const val IMAGE_MANIPULATION_WORK_NAME = "image_manipulation_work"

        // Other keys
        const val OUTPUT_PATH = "blur_filter_outputs"
        const val KEY_IMAGE_URI = "KEY_IMAGE_URI"
        const val TAG_WORKER = "BLUR_WORKER"

        const val DELAY_TIME_MILLIS: Long = 3000
    }
}