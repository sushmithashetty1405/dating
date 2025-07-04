package com.example.demo.util;



import java.util.Comparator;

import com.example.demo.dto.MatchingUser;

public class UserSorting implements Comparator<MatchingUser> 
{
	@Override
	public int compare(MatchingUser o1, MatchingUser o2) {
		if(o1.getAgeDiff()> o2.getAgeDiff()) { 
			return -1;
		}
		else if(o1.getAgeDiff() < o2.getAgeDiff()){
			return 1;
		}
		else if(o1.getAgeDiff()==o2.getAgeDiff()){
			if(o1.getMic()< o2.getMic()) {
				return 1;
			}else if(o1.getMic() > o2.getMic()){
				return -1;
			}
		}
		return 0;
	}
}
