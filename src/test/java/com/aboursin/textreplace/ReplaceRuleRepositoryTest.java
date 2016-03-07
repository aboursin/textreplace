package com.aboursin.textreplace;

import java.io.File;
import java.net.URL;
import java.util.List;

import junit.framework.TestCase;

import org.junit.Test;

import com.aboursin.textreplace.bean.ReplaceRule;
import com.aboursin.textreplace.bean.repository.CsvRepository;
import com.aboursin.textreplace.bean.repository.ReplaceRuleRepository;

/**
 * Test class for {@link ReplaceRuleRepository} class.
 * @see ReplaceRule
 * @author angelo.boursin
 */
public class ReplaceRuleRepositoryTest extends TestCase {
	
	/**
	 * Test with no file.
	 * Expected result : empty list of {@link ReplaceRule}.
	 */
	@Test
	public void test_findAll_nullfile(){
		ReplaceRuleRepository repository = new ReplaceRuleRepository(null, CsvRepository.DEFAULT_SEPARATOR, 
				CsvRepository.DEFAULT_QUOTE_CHARACTER, CsvRepository.DEFAULT_ESCAPE_CHARACTER);
		List<ReplaceRule> rules = repository.findAll();
		assertTrue(rules.isEmpty());
	}
	
	/**
	 * Test with a non existent file.
	 * Expected result : empty list of {@link ReplaceRule}.
	 */
	@Test
	public void test_findAll_nofile(){
		File csv = new File("replacerule/repository/zhjcxhiwyfc");
		
		ReplaceRuleRepository repository = new ReplaceRuleRepository(csv, 
				CsvRepository.DEFAULT_SEPARATOR, CsvRepository.DEFAULT_QUOTE_CHARACTER, CsvRepository.DEFAULT_ESCAPE_CHARACTER);
		List<ReplaceRule> rules = repository.findAll();
		assertTrue(rules.isEmpty());
	}
	
	/**
	 * Test with an empty file.
	 * Expected result : empty list of {@link ReplaceRule}.
	 */
	@Test
	public void test_findAll_empty(){
		
		URL url = Thread.currentThread().getContextClassLoader().getResource("replacerule/repository/test.csv");
		File csv = new File(url.getPath());
		
		ReplaceRuleRepository repository = new ReplaceRuleRepository(csv, CsvRepository.DEFAULT_SEPARATOR, 
				CsvRepository.DEFAULT_QUOTE_CHARACTER, CsvRepository.DEFAULT_ESCAPE_CHARACTER);
		
		List<ReplaceRule> rules = repository.findAll();
		
		// Check 0 record found
		assertEquals(0, rules.size());
	}
	
	/**
	 * Test with an empty file (only header).
	 * Expected result : empty list of {@link ReplaceRule}.
	 */
	@Test
	public void test_findAll_headeronly(){
		
		URL url = Thread.currentThread().getContextClassLoader().getResource("replacerule/repository/test0.csv");
		File csv = new File(url.getPath());
		
		ReplaceRuleRepository repository = new ReplaceRuleRepository(csv, CsvRepository.DEFAULT_SEPARATOR, 
				CsvRepository.DEFAULT_QUOTE_CHARACTER, CsvRepository.DEFAULT_ESCAPE_CHARACTER);
		
		List<ReplaceRule> rules = repository.findAll();
		
		// Check 0 record found
		assertEquals(0, rules.size());
	}
	
	/**
	 * Test with a file containing one record.
	 * Expected result : list of {@link ReplaceRule} containing the matching record.
	 */
	@Test
	public void test_findAll_onerecord(){
		
		URL url = Thread.currentThread().getContextClassLoader().getResource("replacerule/repository/test1.csv");
		File csv = new File(url.getPath());
		
		ReplaceRuleRepository repository = new ReplaceRuleRepository(csv, CsvRepository.DEFAULT_SEPARATOR, 
				CsvRepository.DEFAULT_QUOTE_CHARACTER, CsvRepository.DEFAULT_ESCAPE_CHARACTER);
		
		List<ReplaceRule> rules = repository.findAll();
		
		// Check 1 record found
		assertEquals(1, rules.size());
		
		// Check rule
		ReplaceRule rule = rules.get(0);
		assertEquals("ABC", rule.getFindwhat());
		assertEquals("DEF", rule.getReplacewith());
		assertTrue(rule.getCasesensitive());
		assertFalse(rule.getRegex());
	}
	
	/**
	 * Test with a file containing two records.
	 * Expected result : list of {@link ReplaceRule} containing the two matching records.
	 */
	@Test
	public void test_findAll_multiplerecords(){
		
		URL url = Thread.currentThread().getContextClassLoader().getResource("replacerule/repository/test2.csv");
		File csv = new File(url.getPath());
		
		ReplaceRuleRepository repository = new ReplaceRuleRepository(csv, CsvRepository.DEFAULT_SEPARATOR, 
				CsvRepository.DEFAULT_QUOTE_CHARACTER, CsvRepository.DEFAULT_ESCAPE_CHARACTER);
		
		List<ReplaceRule> rules = repository.findAll();
		
		// Check 2 records found
		assertEquals(2, rules.size());
		
		// Check first rule
		ReplaceRule rule1 = rules.get(0);
		assertEquals("ABC", rule1.getFindwhat());
		assertEquals("DEF", rule1.getReplacewith());
		assertTrue(rule1.getCasesensitive());
		assertFalse(rule1.getRegex());
		
		// Check second rule
		ReplaceRule rule2 = rules.get(1);
		assertEquals("  ", rule2.getFindwhat());
		assertEquals(" ", rule2.getReplacewith());
		assertFalse(rule2.getCasesensitive());
		assertFalse(rule2.getRegex());
	}
	
	/**
	 * Test with a file containing advanced regular expressions.
	 */
	@Test
	public void test_findAll_complex(){
		
		URL url = Thread.currentThread().getContextClassLoader().getResource("replacerule/repository/replacements.txt");
		File csv = new File(url.getPath());
		
		char separator = ';';
		char quote = '"';
		char escape = '¥';
		
		ReplaceRuleRepository repository = new ReplaceRuleRepository(csv, separator, quote, escape);
		
		List<ReplaceRule> rules = repository.findAll();
		
		for (ReplaceRule rule : rules) {
			System.out.println(rule);
		}
	}

}
