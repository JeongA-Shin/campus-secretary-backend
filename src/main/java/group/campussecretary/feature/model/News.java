
//엔티티는 테이블과 매핑되는 것인데, 네이버 뉴스 검색 결과를 굳이 db 테이블에 저장할 필요가 없음
//따라서 엔티티 없이 그냥 네이버 검색 api 호출 결과를 바로 dto로 매핑해서 컨트롤러에서 바로 반환해주면 됨
//즉 service 단에서는 그냥 네이버 뉴스 검색 api를 호출한 결과가 어차피 json이니까 그걸 바로 반환해주기만 하면 되는 거임
//그리고 뉴스 컨트롤러 단에서는 그냥 그 서비스를 호출하면 됨
//같은 이유로 News 리포지토리는 필요 없음 - db에 저장할 필요가 없으므로

//package group.campussecretary.feature.model;
//
//import lombok.*;
//
//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import java.util.List;
//import java.util.UUID;
//
//@Entity
//@Data
//@Builder
//@AllArgsConstructor
//@NoArgsConstructor
//@ToString
//@EqualsAndHashCode
//public class News {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    private UUID id;
//
//
//    private String title;
//
//    //private String items;
//
//}
