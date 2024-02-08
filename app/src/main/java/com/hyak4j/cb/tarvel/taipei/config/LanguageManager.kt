package com.hyak4j.cb.tarvel.taipei.config

class LanguageManager {
    companion object {
        private var currentLanguage = "zh-tw"
        fun setLanguage(language: String) {
            currentLanguage = language
        }

        fun getLanguage(): String {
            return currentLanguage
        }
    }
}