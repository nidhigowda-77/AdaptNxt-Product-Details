package com.product.details.Product.Service;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import com.product.details.Product.Helper.Product;

@Service
public class ProductService {

	/**
	 * @author Nidhi T S
	 * 
	 * this method fetches product details from the url and store it in a DTO
	 * 
	 * @param url String
	 * @return List<Product>
	 * @throws Exception
	 */
    public List<Product> scrapeProductDetails(String url) throws Exception {
    	
        List<Product> products = new ArrayList<>();

        try {
        	
            Document doc = Jsoup.connect(url).get();

            // Fetch the Product category
            Element categoryElement = doc.select("h1.d-inline.body-xs.font-weight-medium").first();
            String category = categoryElement.text();

            Elements productElements = doc.select(".js-productCard");

            int counter = 0;

            // loop the element and add the data to respective product fields
            for ( Element productElement : productElements ) {

                Product product = new Product();

                if ( counter == 11 ) { 
                	
                    break;
                }

                // Extract product details
                product.setProductName(productElement.select(".search-product-name").text());
                product.setItemNumber(productElement.select(".GEffortFindNum").text().replace("Item #: ", ""));
                product.setProductCategory(category);
                product.setProductDescription(productElement.select(".js-skuBullets").text().trim());

                // Extract the item price
                Elements price = productElement.select("span[class*=js-item-price]");

                if ( !price.isEmpty() ) {
                	
                    String itemPrice = price.first().text().trim();
                    product.setProductPrice(itemPrice);
                }

                products.add(product);
                counter++;
            }
            
        } catch (Exception e) {
        	
            e.printStackTrace();
            throw e;
        }

        return products;
    }
}
