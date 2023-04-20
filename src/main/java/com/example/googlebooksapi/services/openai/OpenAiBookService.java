package com.example.googlebooksapi.services.openai;

import com.example.googlebooksapi.dtos.openai.requests.OpenAiDavinciPrompt;
import com.example.googlebooksapi.dtos.openai.response.OpenAiResponse;
import com.example.googlebooksapi.services.http.IHttpRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Service
public class OpenAiBookService {
    private final IHttpRequest _httpFetch;
    private final BookApiPrompts _bookPromptMessages;
    @Value("${OpenAiToken}")
    private String apiToken;

    private final Class<OpenAiDavinciPrompt> _promptDescriptor = OpenAiDavinciPrompt.class;
    private final Class<OpenAiResponse> _responseDescriptor = OpenAiResponse.class;

    private final String Uri = "https://api.openai.com/v1/completions";

    public OpenAiBookService(IHttpRequest httpFetch, BookApiPrompts bookPromptMessages) {
        _httpFetch = httpFetch;
        _bookPromptMessages = bookPromptMessages;
    }

    public String bookSummary(String author, String title, int length){
        var prompt = _bookPromptMessages.summary(author,title,length);
        var content = _httpFetch.postRequest(Uri,_promptDescriptor,prompt,_responseDescriptor,apiToken);
        return getText(content);
    }

    public List<String> recommendedBooks(String description, int maxResults){
        if(description == null)
            return new ArrayList<>(){
                {
                    add("Description is not present");
                }
            };
        var prompt = _bookPromptMessages.similarBooks(description, maxResults);
        var content = _httpFetch.postRequest(Uri, _promptDescriptor,prompt,_responseDescriptor,apiToken);
        var result = getText(content);
        return formatResult(result);
    }

    public List<String> recommendedBooks(String author, String title, int maxResults){
        var prompt = _bookPromptMessages.similarBooks(author,title, maxResults);
        var content = _httpFetch.postRequest(Uri, _promptDescriptor,prompt,_responseDescriptor,apiToken);
        var result = getText(content);
        return formatResult(result);
    }

    private String getText(OpenAiResponse content){
        var choices = content.getChoices();
        var first = choices.stream().findFirst().orElse(null);
        return first != null ? first.getText() : null;
    }

    private List<String> formatResult(String src){
        var strings = new ArrayList<String>();
        var scan = new Scanner(src).useDelimiter(";");
        while (scan.hasNext()){
            var str = scan.next();
            var nStr = str.replace("\n","");
            strings.add(nStr);
        }
        return strings;
    }
}
