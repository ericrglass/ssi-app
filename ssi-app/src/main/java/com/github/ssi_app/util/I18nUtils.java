package com.github.ssi_app.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.github.ssi_servlet.util.I18nResourceUtils;

public class I18nUtils extends I18nResourceUtils {

	public static final List<String> CURRENT_SUPPORTED_LANGUAGES = new ArrayList<String>();
	public static final Map<String, List<String>> CURRENT_SUPPORTED_LANGUAGE_COUNTRIES = new HashMap<String, List<String>>();

	static {
		CURRENT_SUPPORTED_LANGUAGES.add("en");

		List<String> enCountries = new ArrayList<String>();
		// English - Australia
		enCountries.add("AU");
		// English - Belize
		enCountries.add("BZ");
		// English - Canada
		enCountries.add("CA");
		// English - Caribbean
		enCountries.add("CB");
		// English - Ireland
		enCountries.add("IE");
		// English - Jamaica
		enCountries.add("JM");
		// English - New Zealand
		enCountries.add("NZ");
		// English - Philippines
		enCountries.add("PH");
		// English - South Africa
		enCountries.add("ZA");
		// English - Trinidad and Tobago
		enCountries.add("TT");
		// English - United Kingdom
		enCountries.add("GB");
		// English - United States
		enCountries.add("US");
		// English - Zimbabwe
		enCountries.add("ZW");
		CURRENT_SUPPORTED_LANGUAGE_COUNTRIES.put("en", enCountries);

		CURRENT_SUPPORTED_LANGUAGES.add("es");

		List<String> esCountries = new ArrayList<String>();
		// Spanish - Argentina
		esCountries.add("AR");
		// Spanish - Bolivia
		esCountries.add("BO");
		// Spanish - Chile
		esCountries.add("CL");
		// Spanish - Colombia
		esCountries.add("CO");
		// Spanish - Costa Rica
		esCountries.add("CR");
		// Spanish - Dominican Republic
		esCountries.add("DO");
		// Spanish - Ecuador
		esCountries.add("EC");
		// Spanish - El Salvador
		esCountries.add("SV");
		// Spanish - Guatemala
		esCountries.add("GT");
		// Spanish - Honduras
		esCountries.add("HN");
		// Spanish - Mexico
		esCountries.add("MX");
		// Spanish - Nicaragua
		esCountries.add("NI");
		// Spanish - Panama
		esCountries.add("PA");
		// Spanish - Paraguay
		esCountries.add("PY");
		// Spanish - Peru
		esCountries.add("PE");
		// Spanish - Puerto Rico
		esCountries.add("PR");
		// Spanish - Spain
		esCountries.add("ES");
		// Spanish - Uruguay
		esCountries.add("UY");
		// Spanish - Venezuela
		esCountries.add("VE");
		CURRENT_SUPPORTED_LANGUAGE_COUNTRIES.put("es", esCountries);

		CURRENT_SUPPORTED_LANGUAGES.add("fr");

		List<String> frCountries = new ArrayList<String>();
		// French - Belgium
		frCountries.add("BE");
		// French - Canada
		frCountries.add("CA");
		// French - France
		frCountries.add("FR");
		// French - Luxembourg
		frCountries.add("LU");
		// French - Monaco
		frCountries.add("MC");
		// French - Switzerland
		frCountries.add("CH");
		CURRENT_SUPPORTED_LANGUAGE_COUNTRIES.put("fr", frCountries);

		CURRENT_SUPPORTED_LANGUAGES.add("it");

		List<String> itCountries = new ArrayList<String>();
		// Italian - Italy
		itCountries.add("IT");
		// Italian - Switzerland
		itCountries.add("CH");
		CURRENT_SUPPORTED_LANGUAGE_COUNTRIES.put("it", itCountries);

	}

	public static Map<String, String> getCurrentSupportedLanguagesMap(
			Locale curLocale) {
		Map<String, String> curSupLangsMap = new LinkedHashMap<String, String>(
				CURRENT_SUPPORTED_LANGUAGES.size());
		Locale descrLocale = curLocale;

		if (curLocale == null) {
			descrLocale = DEFAULT_LOCALE;
		}

		Map<String, String> langDescrMap = new HashMap<String, String>(
				CURRENT_SUPPORTED_LANGUAGES.size());

		for (String curSupLang : CURRENT_SUPPORTED_LANGUAGES) {
			langDescrMap.put(curSupLang,
					new Locale(curSupLang).getDisplayLanguage(descrLocale));
		}

		List<String> descrList = new ArrayList<String>(langDescrMap.values());
		Collections.sort(descrList);

		for (String descr : descrList) {
			if (!langDescrMap.containsValue(descr)) {
				continue;
			}

			String lang = null;

			for (String curLang : langDescrMap.keySet()) {
				String curDescr = langDescrMap.get(curLang);

				if (descr.equals(curDescr)) {
					lang = curLang;
					break;
				}
			}

			if (lang == null) {
				continue;
			}

			curSupLangsMap.put(lang, descr);
		}

		return curSupLangsMap;
	}

	public static Map<String, Map<String, String>> getCurrentSupportedLanguageCountriesMap(
			Locale curLocale) {
		Map<String, Map<String, String>> curSupLangCountriesMap = new HashMap<String, Map<String, String>>(
				CURRENT_SUPPORTED_LANGUAGES.size());
		Locale descrLocale = curLocale;

		if (curLocale == null) {
			descrLocale = DEFAULT_LOCALE;
		}

		for (String curSupLang : CURRENT_SUPPORTED_LANGUAGES) {
			List<String> countriesList = CURRENT_SUPPORTED_LANGUAGE_COUNTRIES
					.get(curSupLang);

			if ((countriesList == null) || (countriesList.size() == 0)) {
				continue;
			}

			Map<String, String> countriesDescrMap = new HashMap<String, String>();

			for (String curCountry : countriesList) {
				countriesDescrMap.put(curCountry, new Locale(curSupLang,
						curCountry).getDisplayCountry(descrLocale));
			}

			List<String> descrList = new ArrayList<String>(
					countriesDescrMap.values());
			Collections.sort(descrList);

			Map<String, String> countriesMap = new LinkedHashMap<String, String>(
					descrList.size());

			for (String descr : descrList) {
				if (!countriesDescrMap.containsValue(descr)) {
					continue;
				}

				String country = null;

				for (String curCountry : countriesDescrMap.keySet()) {
					String curDescr = countriesDescrMap.get(curCountry);

					if (descr.equals(curDescr)) {
						country = curCountry;
						break;
					}
				}

				if (country == null) {
					continue;
				}

				countriesMap.put(country, descr);
			}

			curSupLangCountriesMap.put(curSupLang, countriesMap);
		}

		return curSupLangCountriesMap;
	}

	public static void loadCultureResourceBundleIntoRequest(
			HttpServletRequest request, String resBaseName, String language,
			String country, String resPackage, boolean javaClass, boolean debug) {
		if ((request == null) || (resBaseName == null)
				|| (resBaseName.trim().length() == 0) || (language == null)
				|| (language.trim().length() == 0)) {
			return;
		}

		Map<String, String> resBundleMap = getCultureResourceBundleMap(
				resBaseName, language, country, resPackage, javaClass, debug);

		if ((resBundleMap == null) || (resBundleMap.size() == 0)) {
			return;
		}

		for (String key : resBundleMap.keySet()) {
			if ((key == null) || (key.trim().length() == 0)) {
				continue;
			}

			String value = resBundleMap.get(key);

			if (value == null) {
				request.setAttribute(key.trim(), "");
			} else {
				StringBuilder valueStr = new StringBuilder(value);
				int pos = valueStr.indexOf("\\u");

				while (pos > -1) {
					String next4Str = "";

					if (valueStr.length() >= pos + 6) {
						next4Str = valueStr.substring(pos + 2, pos + 6);
					}

					if (next4Str.length() == 0) {
						break;
					}

					if (next4Str.matches("^[0-9A-F]+$")) {
						valueStr.insert(pos + 6, ";");
						valueStr.replace(pos, pos + 2, "&#x");
					}

					if (pos + 2 >= valueStr.length()) {
						break;
					}

					pos = valueStr.indexOf("\\u", pos + 2);
				}

				request.setAttribute(key.trim(), valueStr.toString());
			}
		}
	}

	public static void loadCultureResourceBundleIntoRequest(
			HttpServletRequest request, String resBaseName, Locale locale,
			String resPackage, boolean javaClass, boolean debug) {
		if ((request == null) || (resBaseName == null)
				|| (resBaseName.trim().length() == 0) || (locale == null)) {
			return;
		}

		loadCultureResourceBundleIntoRequest(request, resBaseName,
				locale.getLanguage(), locale.getCountry(), resPackage,
				javaClass, debug);
	}

}
