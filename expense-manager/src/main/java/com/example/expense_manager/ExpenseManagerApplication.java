package com.example.expense_manager;


import com.example.expense_manager.contract.request.response.TransactionResponse;
import org.hibernate.Transaction;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.config.Configuration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;



@SpringBootApplication
public class ExpenseManagerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ExpenseManagerApplication.class, args);
	}
@Bean
	public ModelMapper modelMapper(){
		ModelMapper modelMapper = new ModelMapper();
	modelMapper.getConfiguration().setFieldMatchingEnabled(true)
			.setFieldAccessLevel(Configuration.AccessLevel.PRIVATE);

	modelMapper.addMappings(
			new PropertyMap<Transaction, TransactionResponse>() {
				@Override
				protected void configure() {
					map().setUser(source.getUser().getId());
				}
			}
	);return  modelMapper;
}



}
