package com.aboursin.textreplace;

import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.OptionHandlerFilter;

import com.aboursin.textreplace.bean.ProgramOptions;
import com.aboursin.textreplace.bean.ReplaceRule;
import com.aboursin.textreplace.bean.repository.ReplaceRuleRepository;

/**
 * Main class for textreplace program
 * @author angelo.boursin
 */
public class TextReplace {

	/**
	 * Main.
	 * @param args Program arguments
	 * @throws IOException I/O Exception
	 */
	public static void main(String[] args) throws IOException {

		// Manage program options
		ProgramOptions options = new ProgramOptions();
		CmdLineParser parser = new CmdLineParser(options);
		
		try {
	      parser.parseArgument(args);
	    } catch (CmdLineException e) {
	    	System.err.println("Error: " + e.getMessage());
	    	parser.printUsage(System.err);
	    	System.err.println();
	        System.err.println("  Example: java -jar program" + parser.printExample(OptionHandlerFilter.ALL));
	        System.exit(0);
	    }
		
		// Build services
		ReplaceEngine engine = new ReplaceEngine();
		ReplaceRuleRepository repository = new ReplaceRuleRepository(options.replacements, options.separator, options.quote, options.escape);
		
		// Retrieve all replace rules
		List<ReplaceRule> rules = repository.findAll();
		System.out.println(String.format("%s rule(s) found", rules.size()));
		
		// Read source file
		System.out.println(String.format("Reading %s ...", options.input.getAbsolutePath()));
		String inputText = FileUtils.readFileToString(options.input);
		
		// Apply replacements
		System.out.println("Applying rules ...");
		String ouputText = engine.applyRules(inputText, rules);
		
		// Write destination file
		System.out.println(String.format("Writing %s ...", options.output.getAbsolutePath()));
		FileUtils.write(options.output, ouputText);
	}

}
