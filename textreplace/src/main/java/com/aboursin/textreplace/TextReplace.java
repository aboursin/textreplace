package com.aboursin.textreplace;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;

import com.aboursin.textreplace.bean.ReplaceRule;
import com.aboursin.textreplace.bean.repository.ReplaceRuleRepository;

public class TextReplace {

	public static void main(String[] args) throws IOException {

		ReplaceEngine engine = new ReplaceEngine();
		ReplaceRuleRepository repository = new ReplaceRuleRepository();
		
		if(args.length != 2){
			System.out.println("Proper Usage is: java program source_file destination_file");
	        System.exit(0);
		}
		
		// Build source & destination files
		File source = new File(args[0]);
		File destination = new File(args[1]);
		
		// Retrieve all replace rules
		List<ReplaceRule> rules = repository.findAll();
		
		// Read source file
		String sourceText = FileUtils.readFileToString(source);
		
		// Apply replacements
		String resultText = engine.applyRules(sourceText, rules);
		
		// Write destination file
		FileUtils.write(destination, resultText);

	}

}
