package com.hrm.thinkerhouse.services;

import java.util.List;

import com.hrm.thinkerhouse.entities.Branch;

public interface BranchService {

	public List<Branch> getBranchs();
	public Branch getBranch(int idBranch);
	public Branch addBranch(Branch branch);
	public Branch updateBranch(Branch branch);
	public void deleteBranch(int idBranch);
	public long countBranch();
}
