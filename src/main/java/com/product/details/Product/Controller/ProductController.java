package com.product.details.Product.Controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.product.details.Product.Helper.Product;
import com.product.details.Product.Service.ProductService;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import static com.product.details.Product.Constants.Constants.URL;
import static com.product.details.Product.Constants.Constants.FILE_PATH;
import static com.product.details.Product.Constants.Constants.SHEET_NAME;
import static com.product.details.Product.Constants.Constants.PRODUCT_NAME;
import static com.product.details.Product.Constants.Constants.PRODUCT_PRICES;
import static com.product.details.Product.Constants.Constants.PRODUCT_CATEGORY;

import static com.product.details.Product.Constants.Constants.PRODUCT_DESCRIPTIONS;
import static com.product.details.Product.Constants.Constants.ITEM_NUMBER;

@RestController
public class ProductController {

	Logger log = LoggerFactory.getLogger(ProductController.class);

	@Autowired
	private ProductService productService;
	
	

	/**
	 * @author Nidhi T S 
	 * 
	 * In this method we fetched the data from the service and we
	 *         added to the excel file
	 * 
	 * @throws IOException
	 */
	@GetMapping("/fetch")
	public ResponseEntity<String> fetchAndStoreProductDetails() {
		
	    try {
	    	
	        List<Product> products = productService.scrapeProductDetails(URL);

	        // XLSX file path
	        String xlsxFilePath = FILE_PATH;
	        
	        Workbook workbook = new XSSFWorkbook();
	        Sheet sheet = workbook.createSheet(SHEET_NAME);

	        // Create headers
	        Row headerRow = sheet.createRow(0);

	        String[] headers = { PRODUCT_NAME, PRODUCT_PRICES, ITEM_NUMBER, PRODUCT_CATEGORY, PRODUCT_DESCRIPTIONS };

	        for ( int index = 0; index < headers.length; index++) {
	        	
	            Cell cell = headerRow.createCell( index );
	            cell.setCellValue( headers[ index ] );
	        }

	        int rowNum = 1;

	        for ( Product product : products ) {
	        	
	            Row row = sheet.createRow(rowNum++);
	            row.createCell(0).setCellValue(product.getProductName());
	            row.createCell(1).setCellValue(product.getProductPrice());
	            row.createCell(2).setCellValue(product.getItemNumber());
	            row.createCell(3).setCellValue(product.getProductCategory());
	            row.createCell(4).setCellValue(product.getProductDescription());
	        }

	        // Write the workbook to the XLSX file
	        try ( FileOutputStream outputStream = new FileOutputStream( xlsxFilePath ) ) {
	        	
	            workbook.write( outputStream );
	        }

	        workbook.close();

	        return new ResponseEntity<String>("Product details fetched and stored successfully.",HttpStatusCode.valueOf(200));
	        
	    } catch ( IOException e ) {
	    	
	        log.error("Error fetching or storing product details: " + e.getMessage());
	        
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	            .body("Error fetching or storing product details.");
	        
	    } catch ( Exception ex ) {
	    	
	        log.error( ex.getMessage() );
	         
	        return ResponseEntity.status( HttpStatus.INTERNAL_SERVER_ERROR )
	            .body( "An unexpected error occurred." );
	    }
	}

}
