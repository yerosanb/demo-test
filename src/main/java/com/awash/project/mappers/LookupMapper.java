package com.awash.project.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.awash.project.models.LookupModel;

@Mapper
public interface LookupMapper {

	@Insert("insert into lookup_criteria(gradeBasedOn,customerType_criteria,riskLevel,riskScore,scenario,value,maker) values(#{gradeBasedOn},#{customerType_criteria},#{riskLevel},#{riskScore},#{scenario},#{value},#{maker}) ")
	List<LookupModel> saveLookup(LookupModel lookup);
@Update("update lookup_criteria set gradeBasedOn=#{gradeBasedOn},customerType_criteria=#{customerType_criteria},riskLevel=#{riskLevel},riskScore=#{riskScore},scenario=#{scenario},value=#{value},maker=#{maker},changedDate=#{changedDate},approved=0 where id =#{id} ")
	void updateLookup(LookupModel lookup);
@Select("select * from lookup_criteria where status=0")
List<LookupModel> getAllLookup();

@Select("select count(*) from lookup_criteria where status=0")
int COUNT_Lookup();

@Select("select * from lookup_criteria where status=0 ORDER BY gradeBasedOn OFFSET #{offset} ROWS FETCH NEXT #{limit} ROWS ONLY; ")
List<LookupModel> getLookupBypage(@Param("limit") int limit, @Param("offset") int offset);

@Select("select * from lookup_criteria where status=0 and id=#{id}")
LookupModel getLookupById(Long id);

@Update("update lookup_criteria set approved=1,approver=#{approver},approvedDate=#{approvedDate} where id=#{id} ")
void approveLookup(LookupModel lookup);

@Update("update lookup_criteria set status=1,deletedBy=#{deletedBy},deletedDate=#{deletedDate} where id=#{id}")
void deleteLookup(LookupModel lookup);

}
