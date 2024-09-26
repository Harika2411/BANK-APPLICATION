package com.firstexample.democrt.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.firstexample.democrt.Exception.AdminNotFoundException;
import com.firstexample.democrt.entity.Admin;
import com.firstexample.democrt.repository.AdminRepository;

@Service
public class AdminService {
	@Autowired
	private AdminRepository arepo;
	
	public AdminService(AdminRepository arepo)
	{
		
		//System.out.println("DI via constructor");
		this.arepo=arepo;
	}
	
	public Admin createAdmin(Admin admin)
	{
	Admin saved=arepo.save(admin);
		return saved;
	}
	public Admin updateAdmin(Admin updateAdmin,int id)
	{
		Admin admin=fetchAdmin(id);
		Admin updated=null;
		if(admin!=null)
		{
			admin.setUname(updateAdmin.getUname());
			admin.setPassword(updateAdmin.getPassword());
			System.out.println(updateAdmin);
			
		updated = arepo.save(admin);
		}
		return updated;
	}
	public Admin fetchAdmin(int id)
	{
	
		Optional<Admin> admin=arepo.findById(id);
		if(admin.isPresent())
		{
			return admin.get();
		}
		return null;
	}
	public String deleteAdmin(int id)
	{
		arepo.deleteById(id);
		return "Admin with id"+ id +"deleted";
	}
	public String validateAdmin(Admin admin,int id) throws AdminNotFoundException {
		Admin fetched = fetchAdmin(id);
		String uname = admin.getUname();
		String password = admin.getPassword();
		if (fetched.getUname().equals(uname)) {
			if (fetched.getPassword().equals(password))
				return "Success";
			else
				return "Enter the Correct Password";
		}
		return "Wrong Credentials";

	}

}
