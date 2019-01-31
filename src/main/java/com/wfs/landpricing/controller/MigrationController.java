package com.wfs.landpricing.controller;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.wfs.landpricing.service.BasePriceMigrationService;
import com.wfs.landpricing.service.CustomerSalesMarkupMigrationService;
import com.wfs.landpricing.service.PnrMigrationService;
import com.wfs.landpricing.service.ProspectMigrationService;
import com.wfs.landpricing.service.TerminalMigrationService;

/**
 * @author vsrivastava
 * @since 11/19/18
 **/
@RestController
@RequestMapping("/migrate")
  public class MigrationController {

    private static Logger log = LoggerFactory.getLogger(MigrationController.class);

    @Autowired
    private BasePriceMigrationService basePriceMigrationService;
    @Autowired
    private ProspectMigrationService prospectMigrationService;
    @Autowired
    private PnrMigrationService pnrMigrationService;

    @Autowired
    private CustomerSalesMarkupMigrationService customerSalesMarkupMigrationService;
    
    @Autowired
    private TerminalMigrationService terminalMigrationService;

    @PostMapping("/base-price-group")
    public void storeBasePriceGroup(@RequestParam("file") MultipartFile file) {
      String filename = file.getOriginalFilename();
      try (InputStream inputStream = file.getInputStream()) {
        Files.copy(inputStream, Paths.get("/tmp/bpGrp.csv"), StandardCopyOption.REPLACE_EXISTING);
        basePriceMigrationService.migrateBpGrp("/tmp/bpGrp.csv");
      } catch (IOException e) {
        log.error("Failed to store file " + filename, e);
      }
    }
    @PostMapping("/base-price-raw")
    public void migrateBasePrice(@RequestParam("file") MultipartFile file) {
      String filename = file.getOriginalFilename();
      try (InputStream inputStream = file.getInputStream()) {
        Files.copy(inputStream, Paths.get("/tmp/bp.csv"), StandardCopyOption.REPLACE_EXISTING);
        basePriceMigrationService.migrateBasePriceRaw("/tmp/bp.csv");
      } catch (IOException e) {
        log.error("Failed to store file " + filename, e);
      }
    }

    @PostMapping("/base-price")
    public void migrateBasePrice() {
      basePriceMigrationService.migrateBasePrice();
    }


  @PostMapping("/prospect")
  public void storeProspect(@RequestParam("file") MultipartFile file) {
    String filename = file.getOriginalFilename();
    try (InputStream inputStream = file.getInputStream()) {
      Files.copy(inputStream, Paths.get("/tmp/prospect.csv"), StandardCopyOption.REPLACE_EXISTING);
      prospectMigrationService.migrateProspect("/tmp/prospect.csv");
    } catch (IOException e) {
      log.error("Failed to store file " + filename, e);
    }
  }

  @PostMapping("/pnr-prospect")
  public void storeProspectPNR(@RequestParam("file") MultipartFile file) {
    String filename = file.getOriginalFilename();
    try (InputStream inputStream = file.getInputStream()) {
      Files.copy(inputStream, Paths.get("/tmp/prospect.csv"), StandardCopyOption.REPLACE_EXISTING);
      pnrMigrationService.migrateProspectPNR("/tmp/prospect.csv");
    } catch (IOException e) {
      log.error("Failed to store file " + filename, e);
    }
  }


  @PostMapping("/pnr-customer")
  public void storeCustomerPNR(@RequestParam("file") MultipartFile file) {
    String filename = file.getOriginalFilename();
    try (InputStream inputStream = file.getInputStream()) {
      Files.copy(inputStream, Paths.get("/tmp/pnr.csv"), StandardCopyOption.REPLACE_EXISTING);
      pnrMigrationService.migrateCustomerPNR("/tmp/pnr.csv");
    } catch (IOException e) {
      log.error("Failed to store file " + filename, e);
    }
  }


  @PostMapping("/csm")
  public ResponseEntity<String> migrateCSM(@RequestParam("file") MultipartFile file) {
    String filename = file.getOriginalFilename();
    String errors = null;
    try (InputStream inputStream = file.getInputStream()) {
      Files.copy(inputStream, Paths.get("/tmp/csm.csv"), StandardCopyOption.REPLACE_EXISTING);
      errors = customerSalesMarkupMigrationService.migrateCustomerSalesMarkup("/tmp/csm.csv");
    } catch (IOException e) {
      log.error("Failed to store file " + filename, e);
    }
    return new ResponseEntity<>(errors, HttpStatus.OK);
  }


  
  @PostMapping("/terminal-product")
  public void migrateTerminalProduct(@RequestParam("file") MultipartFile file) {
    String filename = file.getOriginalFilename();
    try (InputStream inputStream = file.getInputStream()) {
      Files.copy(inputStream, Paths.get("/tmp/terminal-product.csv"), StandardCopyOption.REPLACE_EXISTING);
       terminalMigrationService.migrateTerminalProduct("/tmp/terminal-product.csv");
    } catch (IOException e) {
      log.error("Failed to store file " + filename, e);
    }
    
  }

}
