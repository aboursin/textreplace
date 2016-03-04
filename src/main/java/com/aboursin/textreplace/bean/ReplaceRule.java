package com.aboursin.textreplace.bean;

import com.aboursin.textreplace.bean.repository.ReplaceRuleRepository;

/**
 * Replace rule bean.
 * @see ReplaceRuleRepository
 * @author angelo.boursin
 */
public class ReplaceRule {

	private String findwhat;
	private String replacewith;
	private Boolean casesensitive;
	private Boolean regex;
	
	public ReplaceRule(){
		super();
	}
	
	public ReplaceRule(final String findwhat, final String replacewith, final Boolean casesensitive, final Boolean regex) {
		super();
		this.findwhat = findwhat;
		this.replacewith = replacewith;
		this.casesensitive = casesensitive;
		this.regex = regex;
	}
	
	public String getFindwhat() {
		return findwhat;
	}
	public void setFindwhat(String findwhat) {
		this.findwhat = findwhat;
	}
	public String getReplacewith() {
		return replacewith;
	}
	public void setReplacewith(String replacewith) {
		this.replacewith = replacewith;
	}
	public Boolean getCasesensitive() {
		return casesensitive;
	}
	public void setCasesensitive(Boolean casesensitive) {
		this.casesensitive = casesensitive;
	}
	public Boolean getRegex() {
		return regex;
	}
	public void setRegex(Boolean regex) {
		this.regex = regex;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString(){
		return String.format("%s\t%s\t[%s] -> [%s]", casesensitive, regex, findwhat, replacewith);
	}
}
