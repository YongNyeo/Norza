package com.example.norza.service.board;


import com.example.norza.domain.FreeBoard;
import com.example.norza.domain.SessionUser;
import com.example.norza.domain.User;
import com.example.norza.dto.FreeBoardSaveDto;
import com.example.norza.repository.FreeBoardRepository;
import com.example.norza.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FreeBoardService implements BoardService<FreeBoard,FreeBoardSaveDto> {
    private final FreeBoardRepository freeBoardRepository;
    private final UserRepository userRepository;

    @Override
    public void save(FreeBoard freeBoard, SessionUser sessionUser) {
        User user = userRepository.findByEmail(sessionUser.getEmail());
        freeBoard.saveUser(user);
        freeBoardRepository.save(freeBoard);
    }

    @Override
    public List<FreeBoard> findAll() {
        return freeBoardRepository.findAll();
    }

    public FreeBoard findById(long id) {
        return freeBoardRepository.findById(id);
    }

    @Override
    public void delete(long id) {
        freeBoardRepository.deleteById(id);
    }

    public void update(long id, FreeBoardSaveDto dto) {
        FreeBoard freeBoard = freeBoardRepository.findById(id);
        freeBoard.DtoToFreeBoard(dto);
        freeBoardRepository.save(freeBoard);
    }
}
