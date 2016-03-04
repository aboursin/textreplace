package com.aboursin.textreplace.bean.repository;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.opencsv.CSVReader;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.HeaderColumnNameMappingStrategy;

/**
 * Abstract CSV repository
 * @author angelo.boursin
 */
public abstract class CsvRepository<T> {
	
	private Class<T> type;
	
	public CsvRepository(Class<T> type){
		this.type = type;
	}
	
	/**
	 * Find all entries retrieved in csv file.
	 * @return List of T
	 */
	public List<T> findAll(){
		List<T> result = new ArrayList<>();
		try {
			result = parseCsvFileToBeans();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * Retrieve current csv file.
	 * @return CSV {@link File}
	 */
	protected abstract File getFile();
	
	/**
	 * Parse a csv file and build a bean for each entry.
	 * @return List of T.
	 * @throws IOException
	 */
	private List<T> parseCsvFileToBeans() throws IOException {
		
		// Check file
		if(getFile() == null){
			throw new FileNotFoundException(String.format("File is null"));
		} else if(!getFile().exists()){
			throw new FileNotFoundException(String.format("File %s does not exists !", getFile().getAbsolutePath()));
		}
		
		// Parse file using opencsv
		CSVReader reader = null;
		try {
			reader = new CSVReader(new FileReader(getFile()));
			HeaderColumnNameMappingStrategy<T> strategy = new HeaderColumnNameMappingStrategy<T>();
			strategy.setType(type);
			CsvToBean<T> csv = new CsvToBean<T>();
			return csv.parse(strategy, reader);
		} finally {
			if (reader != null) {
				reader.close();
			}
		}
	}
	
}
