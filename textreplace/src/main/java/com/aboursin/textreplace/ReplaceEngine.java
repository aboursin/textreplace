package com.aboursin.textreplace;

import java.util.List;
import java.util.regex.Pattern;

import com.aboursin.textreplace.bean.ReplaceRule;

/**
 * Replace engine service.
 * @see ReplaceRule
 * @author angelo.boursin
 */
public class ReplaceEngine {

	/**
	 * Make replacements into a source text using a replacement rule.
	 * @param source Source text
	 * @param rule {@link ReplaceRule} to apply
	 * @return New text
	 */
	public String applyRule(final String source, final ReplaceRule rule){
		
		String findwhat = rule.getFindwhat();
		String replacewith = rule.getReplacewith();
		Boolean casesensitive = rule.getCasesensitive();
		Boolean regularexpression = rule.getRegex();
		int flag = 0;
		
		if(!regularexpression){
			// Escape all special characters
			findwhat = Pattern.quote(rule.getFindwhat());
		}
		
		if(!casesensitive){
			// Set the case insensitive flag
			flag = Pattern.CASE_INSENSITIVE;
		}

		// Replace all
		return Pattern.compile(findwhat, flag).matcher(source).replaceAll(replacewith);
	}
	
	/**
	 * Make replacements into a source text using a list of replacement rule.
	 * @param source Source text
	 * @param rules List of {@link ReplaceRule} to apply
	 * @return New text
	 */
	public String applyRules(final String source, final List<ReplaceRule> rules){
		
		String result = source;
		
		for (ReplaceRule rule : rules) {
			result = applyRule(result, rule);
		}
		
		return result;
	}

}
