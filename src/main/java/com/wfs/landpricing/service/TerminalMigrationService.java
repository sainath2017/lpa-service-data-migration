package com.wfs.landpricing.service;

import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.wfs.landpricing.dao.TerminalProductMappingDao;
import com.wfs.landpricing.dto.ProductTerminalDto;
import com.wfs.landpricing.mapper.TerminalProductMappingMapper;
import com.wfs.landpricing.model.TerminalProductMapping;

@Service
public class TerminalMigrationService {

	private static Logger log = LoggerFactory.getLogger(TerminalMigrationService.class);

	@Autowired
	private TerminalProductMappingMapper mapper;
	
	@Autowired
	private TerminalProductMappingDao terminalProductMappingDao;

	@Transactional
	public void migrateTerminalProduct(String fileName) {
		TerminalProductMapping entity = null;
		try {
			Reader reader = Files.newBufferedReader(Paths.get(fileName));
			CsvToBean<ProductTerminalDto> csvToBean = new CsvToBeanBuilder(reader).withType(ProductTerminalDto.class)
					.withOrderedResults(false).withIgnoreLeadingWhiteSpace(true).build();
			Iterator<ProductTerminalDto> csvBeanIterator = csvToBean.iterator();

			int lineCount = 0;

			while (csvBeanIterator.hasNext()) {
				++lineCount;
				ProductTerminalDto dto = csvBeanIterator.next();
				entity = mapper.toModel(dto);				
				terminalProductMappingDao.save(entity);

				log.info("line count {}", lineCount);
			}
		} catch (Exception e) {
			log.info("duplicate entity = {}", entity);
			log.error("Error Migrating Terminal Product Mapping ", e);
		}
	}

}
