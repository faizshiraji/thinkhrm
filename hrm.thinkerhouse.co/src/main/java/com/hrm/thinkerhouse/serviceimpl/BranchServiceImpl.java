package com.hrm.thinkerhouse.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hrm.thinkerhouse.entities.Branch;
import com.hrm.thinkerhouse.repo.BranchRepo;
import com.hrm.thinkerhouse.services.BranchService;

@Service
public class BranchServiceImpl implements BranchService {

	@Autowired
	public BranchRepo branchRepo;
	
	@Override
	public List<Branch> getBranchs() {
		return branchRepo.findAll();
	}

	@Override
	public Branch getBranch(int idBranch) {
		return branchRepo.findById(idBranch).get();
	}

	@Override
	public Branch addBranch(Branch branch) {
		return branchRepo.save(branch);
	}

	@Override
	public Branch updateBranch(Branch branch) {
		return branchRepo.save(branch);
	}

	@Override
	public void deleteBranch(int idBranch) {
		branchRepo.deleteById(idBranch);
	}

	@Override
	public long countBranch() {
		return branchRepo.count();
	}

}
