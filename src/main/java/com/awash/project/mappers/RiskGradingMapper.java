package com.awash.project.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.awash.project.models.TransactionFrequencyModel;
@Mapper
public interface RiskGradingMapper {
	
	@Select("select * from transaction_frequency where status=0  ")
	List<TransactionFrequencyModel> transactionfrequency_all();

	@Select("select * from transaction_frequency where status=0 and customerType=#{customerType} ")
	List<TransactionFrequencyModel> getFrequency(String customerType);

	@Select("SELECT * FROM transaction_frequency where status=0 and customerType=#{customerType} ORDER BY accountId OFFSET #{offset} ROWS FETCH NEXT #{limit} ROWS ONLY; ")
	List<TransactionFrequencyModel> transactionfrequencyBypage(@Param("customerType") String customerType, @Param("limit") int limit, @Param("offset") int offset);

	@Select("SELECT count(*) FROM transaction_frequency where status=0 and customerType=#{customerType} ")
	int COUNT_transactionFrequencyBasedOn_CustomerType(String customerType);
	@Select("SELECT * FROM transaction_frequency where status=0 and accountId=#{searchKey} or accountName like concat('%',#{searchKey},'%')")
	List<TransactionFrequencyModel> customer_profile(String searchKey);

	

}
