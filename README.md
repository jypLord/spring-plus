# SPRING PLUS
## LEVEL 1-3 JPA의 이해

기존 pagable만 있었던 할 일 조회에 **날씨**와**조회 기간**을 추가하기 위해 **DTO와 JPQL을 변경하였습니다.**
상기한 조건을 안 넣을 수도 있다는 조건도 있었기 때문에 DTO에 디폴트 값을 설정해주는 메서드를 추가하였습니다.

```java
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
```
링크: https://github.com/jypLord/spring-plus/blob/level-1-3/src/main/java/org/example/expert/domain/todo/dto/request/GetTodoRequest.java

JQPL은 다음과 같습니다. 
weather이 NULL일 수도 있기 때문에 WHERE 절에 IS NULL을 넣어주었습니다. IS NULL을 넣지 않으면 만약 weather이 null일 때 ```WHERE weather = null``` 이 되기 떄문에 의도치 않은 결과가 나옵니다.


```java
    @Query("SELECT t FROM Todo t LEFT JOIN FETCH t.user u " +
            "WHERE (:weather IS NULL OR t.weather =: weather) AND t.modifiedAt BETWEEN :startDate AND :endDate")
    Page<Todo> findAllByWeatherAndOrderByModifiedAtDesc(Pageable pageable,
                                                        @Param("weather") String weather,
                                                        @Param("startDate") LocalDate startDate,
                                                        @Param("endDate") LocalDate endDate);
```
링크: https://github.com/jypLord/spring-plus/blob/level-1-3/src/main/java/org/example/expert/domain/todo/repository/TodoRepository.java

