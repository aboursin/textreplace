package com.aboursin.textreplace;

import org.junit.Test;

import com.aboursin.textreplace.ReplaceEngine;
import com.aboursin.textreplace.bean.ReplaceRule;

import junit.framework.TestCase;

/**
 * Test class for {@link ReplaceEngine} class.
 * @author angelo.boursin
 */
public class ReplaceEngineTest extends TestCase {

	/**
	 * Test replacements with insensitive case and normal expression.
	 */
	@Test
	public void test_apply_caseinsensitive_normalex(){
		
		ReplaceEngine engine = new ReplaceEngine();
		
		String source = "Foo foo xFoo xfoo Foox foox xFoox xfoox .*.";

		// 'too' does not appear into source text : result is equal to source text
		assertEquals(source, engine.applyRule(source, new ReplaceRule("too", "bar", false, false)));
		
		// 'x.*x' does not appear into source text (regex = false) : result is equal to source text
		assertEquals(source, engine.applyRule(source, new ReplaceRule("x.*x", "bar", false, false)));
		
		// All '.*' occurrences are replaced with 'bar'
		assertEquals("Foo foo xFoo xfoo Foox foox xFoox xfoox bar.", engine.applyRule(source, new ReplaceRule(".*", "bar", false, false)));
		
		// All 'foo' occurrences are replaced with 'bar'
		assertEquals("bar bar xbar xbar barx barx xbarx xbarx .*.", engine.applyRule(source, new ReplaceRule("foo", "bar", false, false)));
		
		// All 'FoO' occurrences are replaced with 'bar'
		assertEquals("bar bar xbar xbar barx barx xbarx xbarx .*.", engine.applyRule(source, new ReplaceRule("FoO", "bar", false, false)));
	}
	
	/**
	 * Test replacements with sensitive case and normal expression.
	 */
	@Test
	public void test_apply_casesensitive_normalex(){
		
		ReplaceEngine engine = new ReplaceEngine();
		
		String source = "Foo foo xFoo xfoo Foox foox xFoox xfoox .*.";
		
		// 'too' does not appear into source text : result is equal to source text
		assertEquals(source, engine.applyRule(source, new ReplaceRule("too", "bar", true, false)));
		
		// 'x.*x' does not appear into source text (regex = false) : result is equal to source text
		assertEquals(source, engine.applyRule(source, new ReplaceRule("x.*x", "bar", true, false)));
		
		// All '.*' occurrences are replaced with 'bar'
		assertEquals("Foo foo xFoo xfoo Foox foox xFoox xfoox bar.", engine.applyRule(source, new ReplaceRule(".*", "bar", true, false)));
		
		// All 'foo' (case sensitive) occurrences are replaced with 'bar'
		assertEquals("Foo bar xFoo xbar Foox barx xFoox xbarx .*.", engine.applyRule(source, new ReplaceRule("foo", "bar", true, false)));
		
		// All 'Foo' (case sensitive) occurrences are replaced with 'bar'
		assertEquals("bar foo xbar xfoo barx foox xbarx xfoox .*.", engine.applyRule(source, new ReplaceRule("Foo", "bar", true, false)));
	}
	
	/**
	 * Test replacements with insensitive case and regular expression.
	 */
	@Test
	public void test_apply_caseinsensitive_regex(){
		
		ReplaceEngine engine = new ReplaceEngine();
		
		String source = "Foo foo xFoo xfoo Foox foox xFoox xfoox .*.";
		
		// All 'x.*x' occurrences are replaced with 'bar'
		assertEquals("Foo foo bar .*.", engine.applyRule(source, new ReplaceRule("X.*X", "bar", false, true)));
		assertEquals("Foo foo bar .*.", engine.applyRule(source, new ReplaceRule("x.*x", "bar", false, true)));
		
		// All 'x\\w*x' occurrences are replaced with 'bar'
		assertEquals("Foo foo xFoo xfoo Foox foox bar bar .*.", engine.applyRule(source, new ReplaceRule("X\\w*X", "bar", false, true)));
		assertEquals("Foo foo xFoo xfoo Foox foox bar bar .*.", engine.applyRule(source, new ReplaceRule("x\\w*x", "bar", false, true)));
		
		// ... using a group
		assertEquals("Foo foo xFoo xfoo Foox foox [Foo] [foo] .*.", engine.applyRule(source, new ReplaceRule("X(\\w*)X", "[$1]", false, true)));
	}
	
	/**
	 * Test replacements with sensitive case and regular expression.
	 */
	@Test
	public void test_apply_casesensitive_regex(){
		
		ReplaceEngine engine = new ReplaceEngine();
		
		String source = "Foo foo xFoo xfoo Foox foox xFoox xfoox .*.";
		
		// All 'x.*x' (case sensitive) occurrences are replaced with 'bar'
		assertEquals(source, engine.applyRule(source, new ReplaceRule("X.*X", "bar", true, true)));
		assertEquals("Foo foo bar .*.", engine.applyRule(source, new ReplaceRule("x.*x", "bar", true, true)));
		
		// All 'x\\w*x' (case sensitive) occurrences are replaced with 'bar'
		assertEquals(source, engine.applyRule(source, new ReplaceRule("X\\w*X", "bar", true, true)));
		assertEquals("Foo foo xFoo xfoo Foox foox bar bar .*.", engine.applyRule(source, new ReplaceRule("x\\w*x", "bar", true, true)));
		
		// ... using a group
		assertEquals("Foo foo xFoo xfoo Foox foox [Foo] [foo] .*.", engine.applyRule(source, new ReplaceRule("x(\\w*)x", "[$1]", true, true)));
	}
	
	/**
	 * Text replacements with an advanced regular expression.
	 */
	@Test
	public void test_apply_complex(){
		
		ReplaceEngine engine = new ReplaceEngine();
		
		String source = "I am {\b1}not{\b0} amused.";
		assertEquals("I am <b>not</b> amused.", engine.applyRule(source, new ReplaceRule("\\{\\\b.+?\\}(.+?)\\{\\\b0\\}", "\\<b\\>$1\\</b\\>", false, true)));
	}
	
}
