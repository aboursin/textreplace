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

	private final File csv;
	private final static String DEFAULT_FILENAME = "replacerules.csv";
	
	public ReplaceRuleRepository() {
		super(ReplaceRule.class);
		this.csv = new File(DEFAULT_FILENAME);
	}
	
	public ReplaceRuleRepository(final File csv) {
		super(ReplaceRule.class);
		this.csv = csv;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected File getFile() {
		return csv;
	}
	
}
