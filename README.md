# SPRING PLUS
## LEVEL 1-4 컨트롤러 테스트의 이해

TDD 스타일로 만들어진 테스트코드입니다. 
원래 의도는 실패하기였으나 then 부분의 기대값이 ```mockMvc.andExpect(JsonPath("$.status").value(HttpStatus.OK()))``` 로 되어있었습니다.

해당 부분을 fix하였습니다.
```java
 @Test
    void todo_단건_조회_시_todo가_존재하지_않아_예외가_발생한다() throws Exception {
        // given
        long todoId = 1L;

        // when
        when(todoService.getTodo(todoId))
                .thenThrow(new InvalidRequestException("Todo not found"));

        // then
        mockMvc.perform(get("/todos/{todoId}", todoId))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.status").value(HttpStatus.NOT_FOUND.name()))
                .andExpect(jsonPath("$.code").value(HttpStatus.NOT_FOUND.value()))
                .andExpect(jsonPath("$.message").value("Todo not found"));
    }
}
```




