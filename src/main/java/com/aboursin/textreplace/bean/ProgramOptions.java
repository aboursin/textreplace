package com.aboursin.textreplace.bean;

import java.io.File;

import org.kohsuke.args4j.Option;

import com.aboursin.textreplace.bean.repository.CsvRepository;

/**
 * Program options.
 * Using args4j !
 * @author angelo.boursin
 */
public class ProgramOptions {

	@Option(name = "-i", aliases="--input", usage = "input file", metaVar = "INPUT", required = true)
	public File input;
	
	@Option(name = "-o", aliases="--output", usage = "output file", metaVar = "OUTPUT")
	public File output = new File("output.txt");
	
	@Option(name = "-r", aliases="--replacements", usage = "replacement file", metaVar = "REPLACEMENTS", required = true)
	public File replacements;
	
	@Option(name = "-s", aliases="--separator", usage = "separator character", metaVar = "C")
	public char separator = CsvRepository.DEFAULT_SEPARATOR;
	
	@Option(name = "-q", aliases="--quote", usage = "quote character", metaVar = "C")
	public char quote = CsvRepository.DEFAULT_QUOTE_CHARACTER;
	
	@Option(name = "-e", aliases="--espace", usage = "escape character", metaVar = "C")
	public char escape = CsvRepository.DEFAULT_ESCAPE_CHARACTER;
	
}
