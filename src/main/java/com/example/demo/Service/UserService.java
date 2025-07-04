package com.example.demo.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.DAO.UserDAO;
import com.example.demo.dto.MatchingUser;
import com.example.demo.entity.User;
import com.example.demo.util.UserGender;
import com.example.demo.util.UserSorting;

@Service
public class UserService {

    @Autowired
    UserDAO userdao;

    public ResponseEntity<?> save(User u) {
        User saveduser = userdao.save(u);
        return ResponseEntity.status(HttpStatus.CREATED).body(saveduser);
    }

    public ResponseEntity<?> findall() {
        List<User> list = userdao.findall();
        return ResponseEntity.status(HttpStatus.OK).body(list);
    }

    public ResponseEntity<?> findbyid(int id) {
        Optional<User> optional = userdao.findbyid(id);
        if (optional.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(optional.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No data found");
        }
    }

    public ResponseEntity<?> deletebyid(int id) {
        Optional<User> optional = userdao.findbyid(id);
        if (optional.isPresent()) {
            userdao.deletebyid(id);
            return ResponseEntity.status(HttpStatus.OK).body("ID deleted");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("ID not found");
    }

    public ResponseEntity<?> findAllMaleUsers() {
        List<User> maleUsers = userdao.findAllMaleUsers();
        if (maleUsers.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No male users present");
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(maleUsers);
        }
    }

    public ResponseEntity<?> findallfemale() {
        List<User> list = userdao.findAllfemaleUsers();
        if (list.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No female users present");
        }
        return ResponseEntity.status(HttpStatus.OK).body(list);
    }

    public ResponseEntity<?> findbestmatch(int id, int top) {
        Optional<User> optional = userdao.findbyid(id);
        if (optional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Invalid user ID");
        }

        User user = optional.get();
        List<User> users;

        if (user.getGender().equals(UserGender.MALE)) {
            users = userdao.findAllfemaleUsers();
        } else {
            users = userdao.findAllMaleUsers();
        }

        List<MatchingUser> matchingUsers = new ArrayList<>();
        for (User u : users) {
            MatchingUser mu = new MatchingUser();
            mu.setId(u.getId());
            mu.setName(u.getName());
            mu.setPhone(u.getPhone());
            mu.setAge(u.getAge());
            mu.setInterests(u.getInterest());
            mu.setGender(u.getGender());

            mu.setAgeDiff(Math.abs(user.getAge() - u.getAge()));

            List<String> interests1 = user.getInterest();
            List<String> interests2 = u.getInterest();

            int mic = 0;
            for (String s : interests1) {
                if (interests2.contains(s)) {
                    mic++;
                }
            }
            mu.setMic(mic);

            matchingUsers.add(mu);
        }

        Comparator<MatchingUser> c = new UserSorting(); // Ensure this class exists and implements Comparator<MatchingUser>
        Collections.sort(matchingUsers, c);

        List<MatchingUser> result = new ArrayList<>();
        for (MatchingUser mu : matchingUsers) {
            if (top-- == 0) break;
            result.add(mu);
        }

        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

	public ResponseEntity<?> searchbyname(String letters) {
		
		List <User> users=userdao.searchbyname("%"+letters+"%");
		if(users.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("no user found:"+letters);
		}
		else {
			return ResponseEntity.status(HttpStatus.OK).body(users);
			
		}
	}
	//by email

	public ResponseEntity<?> searchbyemail(String letters) {
	List<User> users=userdao.searchbyemail("%"+letters+"%");
	
	
	if(users.isEmpty()) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("no user found:"+letters);
	}
	else {
		return ResponseEntity.status(HttpStatus.OK).body(users);
		
	}
		
	}
}
