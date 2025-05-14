package org.example.expert.domain.todo.dto.request;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

@Getter
@RequiredArgsConstructor
public class GetTodoRequest {
    private final String weather;
    private final LocalDate startDate;
    private final LocalDate endDate;

    // weather 이 null 인지 검증
    public boolean hasWeather(){
        return this.weather != null;
    }
    public LocalDate getStartDateOrDefault(){
        if(this.startDate==null){
             return LocalDate.parse("0000-01-01");
        }
        return this.startDate;
    }

    public LocalDate getEndDateOrDefault(){
        if(this.endDate==null){
            return LocalDate.now();
        }
        return this.endDate;
    }
}
