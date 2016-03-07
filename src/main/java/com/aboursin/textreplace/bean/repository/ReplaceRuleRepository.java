package com.aboursin.textreplace.bean.repository;

import java.io.File;

import com.aboursin.textreplace.bean.ReplaceRule;

/**
 * Replace rule repository.
 * @see ReplaceRule
 * @see CsvRepository
 * @author angelo.boursin
 */
public class ReplaceRuleRepository extends CsvRepository<ReplaceRule> {
	
	private File file;
	private char separator;
	private char quote;
	private char escape;
	
	public ReplaceRuleRepository() {
		super(ReplaceRule.class);
	}
	
	public ReplaceRuleRepository(final File file, char separator, char quote, char escape) {
		super(ReplaceRule.class);
		this.file = file;
		this.separator = separator;
		this.quote = quote;
		this.escape = escape;
	}

	@Override
	public File getFile() {
		return file;
	}
	
	public void setFile(File file){
		this.file = file;
	}

	@Override
	public char getSeparator() {
		return separator;
	}
	
	public void setSeparator(char separator){
		this.separator = separator;
	}

	@Override
	public char getQuote() {
		return quote;
	}
	
	public void setQuote(char quote){
		this.quote = quote;
	}

	@Override
	public char getEscape() {
		return escape;
	}
	
	public void escape(char escape){
		this.escape = escape;
	}

}
