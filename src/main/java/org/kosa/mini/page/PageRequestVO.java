package org.kosa.mini.page;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Positive;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor // 모든 인자를 전달
@NoArgsConstructor
public class PageRequestVO {

    @Builder.Default
    @Min(value = 1) // pageNo 음수가 되면 안 됨.
    @Positive // 이게 동작하려면 pom.xml -> validation 추가
    private int pageNo = 1;

    @Builder.Default
    @Min(value = 10) // 한 페이지에 10건
    @Max(value = 100) // 많게는 100건
    @Positive
    private int size = 10;

    private String link; // get 방식으로 데이터를 설정할 용도로 만들었는데 아직 안 씀

    private String searchKey; // 입력받은 서치키

    public int getSkip(){ // 위치
        return (pageNo - 1) * size; // 이 부분 원래 10으로 되어있었는데 size로 수정해야함!!!
    }

    public String getLink() {
        if(link == null){
            StringBuilder builder = new StringBuilder();
            builder.append("page=" + this.pageNo);
            builder.append("&size=" + this.size);

            //검색어가 존재할 경우
            if (this.searchKey != null && this.searchKey.length() != 0) {
                try {
                    builder.append("&searchKey=" + URLEncoder.encode(this.searchKey,"UTF-8"));
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }
            link = builder.toString();
        }
        return link;
    }
}
