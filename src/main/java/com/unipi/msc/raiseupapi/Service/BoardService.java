package com.unipi.msc.raiseupapi.Service;

import com.unipi.msc.raiseupapi.Interface.IBoard;
import com.unipi.msc.raiseupapi.Model.Board;
import com.unipi.msc.raiseupapi.Model.Step;
import com.unipi.msc.raiseupapi.Model.User;
import com.unipi.msc.raiseupapi.Repository.BoardRepository;
import com.unipi.msc.raiseupapi.Repository.StepRepository;
import com.unipi.msc.raiseupapi.Repository.UserRepository;
import com.unipi.msc.raiseupapi.Request.BoardRequest;
import com.unipi.msc.raiseupapi.Response.BoardPresenter;
import com.unipi.msc.raiseupapi.Response.GenericResponse;
import com.unipi.msc.raiseupapi.Response.MultipleBoardPresenter;
import com.unipi.msc.raiseupapi.Shared.ErrorMessages;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
@RequiredArgsConstructor
public class BoardService implements IBoard {
    private final BoardRepository boardRepository;
    private final UserRepository userRepository;
    private final StepRepository stepRepository;

    @Override
    public ResponseEntity<?> getBoards() {
        List<MultipleBoardPresenter> presenters = new ArrayList<>();
        boardRepository.findAll().forEach(board -> presenters.add(MultipleBoardPresenter.getPresenter(board)));
        return GenericResponse.builder().data(presenters).build().success();
    }

    @Override
    public ResponseEntity<?> getBoard(Long boardId) {
        return null;
    }

    @Override
    public ResponseEntity<?> createBoard(BoardRequest request) {
        List<User> users = userRepository.findUsersByIdIn(request.getEmployeesId()).orElse(null);
        if (users == null) return GenericResponse.builder().message(ErrorMessages.USER_NOT_FOUND).build().badRequest();
        if (request.getColumns() == null) return GenericResponse.builder().message(ErrorMessages.NO_COLUMNS_FOUND).build().badRequest();

        List<Step> steps = new ArrayList<>();
        for (String column : request.getColumns()){
            Step step = stepRepository.save(Step.builder()
                    .title(column)
                    .build());
            steps.add(step);
        }

        Board board = boardRepository.save(Board.builder()
                .title(request.getTitle())
                .date(new Date().getTime())
                .steps(steps)
                .users(users)
                .build());

        for (User user : users) {
            user.getBoards().add(board);
            userRepository.save(user);
        }

        return GenericResponse.builder().data(BoardPresenter.getPresenter(board)).build().success();
    }
}
