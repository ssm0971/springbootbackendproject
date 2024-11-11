//package com.example.hope_dog.controller.dogmap.map;

//@Slf4j
//@RestController
//@RequestMapping("/api")
//@RequiredArgsConstructor
//public class DogMapRestController {

//    private final RestTemplate restTemplate;
//    private DogMapService dogMapService;

//    @Autowired
//    public DogMapRestController(RestTemplate restTemplate) {
//        this.restTemplate = restTemplate;
//    }

//    @GetMapping("/dogmap")
//    public ApiDTO getDogMapList() throws URISyntaxException {
//        String baseURL = "https://openapi.gg.go.kr/OrganicAnimalProtectionFacilit";
//        String KeyCode = "3347ee61ca704a5c83c692ced1cd2703";
//        String Type = "Json";
////        String Service = "69e33fdd12f748239a4e84bb38252f98";
//        String Service = "OrganicAnimalProtectionFacilit";
//        Integer pIndex = 1;    // 페이지 인덱스
//        Integer pSize = 5;   // 페이지 사이즈
////        String sumYy = "SUM_YY=2023";
////        + "&Service=" + Service + "&pIndex=" + pIndex + "&pSize=" + pSize
//        String url = baseURL + "?ServiceKey=" + KeyCode + "&Type=" + Type;
////        String url = (baseURL + "?Key=" + KeyCode + "&Type=" + Type + "&pIndex=" + pIndex + "&pSize=" + pSize);
////        String url = (baseURL + "?Key=" + KeyCode +  "&Type=" + Type + "&" + sumYy);
////        String url = (baseURL + "?Key=" + KeyCode +  "&Type=" + Type );
////        String url = (baseURL + "?");
////        String url = baseURL + "/dogmap";
//        // URL 인코딩을 사용하여 쿼리 파라미터를 생성합니다.
//        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(baseURL)
//                .queryParam("Key", KeyCode)
//                .queryParam("Type", Type)
//                .queryParam("pIndex", pIndex)
//                .queryParam("pSize", pSize);
//
//        URI uri = uriBuilder.build().toUri();
//
//        log.info("===========확인 Request URI : " + uri); // URI 확인
//
//        HttpHeaders headers = new HttpHeaders();
//        headers.set("Accept", "application/json");
//        HttpEntity<String> entity = new HttpEntity<>(headers);
//
//        // API 요청
//        try {
//            // API 호출 및 응답 처리
//            ResponseEntity<ApiDTO> response = restTemplate.exchange(uri, HttpMethod.GET, entity, ApiDTO.class); //얘가 문제인건 확실함
//            ApiDTO apiDTO = response.getBody();  // 응답 본문을 ApiDTO 객체로 변환
//            log.info("API Response Status: " + response.getStatusCode());  // 상태 코드 확인
//            log.info("API Response Body: " + apiDTO.toString());  // 응답 내용 로그
//            return apiDTO;
//        } catch (HttpServerErrorException e) {
//            log.error("Server error during API request: " + e.getMessage());
//            log.error("Response body: " + e.getResponseBodyAsString());
//            throw e;
//        } catch (Exception e) {
//            log.error("Unexpected error during API request: " + e.getMessage());
//            throw e;
//        }
//    }

//    @Autowired
//    private final DogMapService dogMapService;
//
//    // 생성자 주입
//    public DogMapRestController(DogMapService dogMapService) {
//        this.dogMapService = dogMapService;
//    }

//    @PostMapping("/dogmap")
//    public DogMapApiDTO getDogMapList() {
//        String baseURL = "https://openapi.gg.go.kr/";
//        String service = "OrganicAnimalProtectionFacilit";
//        String keyCode = "cb4a8539e39647ce8a9bc10f33030f55";
//        String type = "json";  // 'Type=json' -> 'json'
//        String pIndex = "1";    // 페이지 인덱스
//        String pSize = "100";   // 페이지 사이즈
//
//        String uri = String.format("%s%s?Key=%s&Type=%s&pIndex=%s&pSize=%s",
//                baseURL, service, keyCode, type, pIndex, pSize);
//
//        log.info("Request URI: " + uri);  // URI 확인
//
//        try {
//            // DogMapService를 사용하여 API 호출
//            DogMapApiDTO dogMapApiDTO = dogMapService.getDogMapList(uri);
//
//            // 정상 응답일 경우
//            if (dogMapApiDTO != null && dogMapApiDTO.getHead() != null && !dogMapApiDTO.getHead().isEmpty()) {
//                DogMapApiDTO.Head head = dogMapApiDTO.getHead().get(0);
//                log.info("API 호출 결과: " + head);
//                if ("INFO-000".equals(head.getResult().getCode())) {
//                    return dogMapApiDTO; // 정상 응답
//                } else {
//                    log.error("API 호출 결과 오류: " + head.getResult().getMessage());
//                }
//            } else {
//                log.error("API 호출 결과: head 정보가 없습니다.");
//            }
//        } catch (Exception e) {
//            log.error("API 호출 중 오류 발생: ", e); // 오류 처리
//        }
//
//        return new DogMapApiDTO(); // 오류가 발생한 경우 빈 객체 반환
//    }

//    @PostMapping("/dogmap")
//    public DogMapDTO getDogMapList() {
//        String baseURL = "https://openapi.gg.go.kr/";
//        String service = "OrganicAnimalProtectionFacilit";
//        String keyCode = "cb4a8539e39647ce8a9bc10f33030f55";
//        String Type = "Type=json";
//        String pIndex = "pIndex=1";
//        String pSize = "pSize=100";
//
//        URI uri = URI.create(baseURL + service + "?Key=" + keyCode + "&" + Type + "&" + pIndex + "&" + pSize);
//        log.info("Request URI 확인 : " + uri); // URI 확인
//
//        DogMapDTO dogMapDTO = new DogMapDTO();
////        dogMapDTO.setService(service);
//
//        log.info("dogMapDTO : " + dogMapDTO);
//
//        try {
//            // RestTemplate 사용
//            HttpHeaders headers = new HttpHeaders();
//            RestTemplate restTemplate = new RestTemplate();
//            HttpEntity<?> entity = new HttpEntity<>(headers);
//
//            // ResponseEntity를 사용해 응답을 직접 처리
//            ResponseEntity<DogMapDTO> response = restTemplate.exchange(uri, HttpMethod.GET, entity, DogMapDTO.class);
//            log.info("API 응답: " + response.getStatusCode());
//            log.info("header : " + response.getHeaders());
//            log.info("response : " + response.getBody());
//            // 응답 처리
//            dogMapDTO = response.getBody();
//            log.info("dogMapDTO: " + dogMapDTO); // 응답 로그
//
//            if (dogMapDTO == null) {
//                if (dogMapDTO.getHead() != null && !dogMapDTO.getHead().isEmpty()) {
//                    DogMapDTO.Head head = dogMapDTO.getHead().get(0);
//                    log.info("DogMapDTO.head: " + head);
//                    if (head.getResult() != null && "INFO-000".equals(head.getResult().getCode())) {
//                        return dogMapDTO; // 정상 응답
//                    } else {
//                        log.error("API 호출 결과 오류: " + head.getResult().getMessage());
//                    }
//                } else {
//                    log.error("헤드 정보가 없습니다.");
//                }
//            } else {
//                log.error("응답이 null입니다.");
//            }
//        } catch (HttpClientErrorException | HttpServerErrorException e) {
//            log.error("API 호출 에러: HTTP 상태 코드 " + e.getStatusCode(), e);
//        } catch (Exception e) {
//            log.error("Error occurred while fetching data: ", e); // 일반 오류 로그 출력
//        }
//
//        return dogMapDTO; // 오류가 발생한 경우 null 반환
//    }


//    @PostMapping("/dogmap")
//    public String getDogMapList(Model model) {
//        String baseURL = "https://openapi.gg.go.kr/OrganicAnimalProtectionFacilit";
//        String keyCode = "cb4a8539e39647ce8a9bc10f33030f55";
//
//        URI uri = URI.create(baseURL + "?Key=" + keyCode + "&Type=json");
//        log.info("Request URI: " + uri); // URI 확인
//
//        try {
//            DogMapDTO response = restTemplate.getForObject(uri, DogMapDTO.class);
//            log.info("Response: " + response); // 응답 로그
//
//            if (response != null) {
//                // head 정보를 확인하여 결과 코드 및 메시지 체크
//                if (response.getHead() != null && !response.getHead().isEmpty()) {
//                    DogMapDTO.Head head = response.getHead().get(0);
//                    log.info("DogMapDTO.head: " + head);
//                    if (head.getResult() != null && "INFO-000".equals(head.getResult().getResultCode())) {
//                        model.addAttribute("dogMapDTO", response);
//                    } else {
//                        model.addAttribute("error", "API 호출 결과 오류: " + head.getResult().getResultMessage());
//                    }
//                } else {
//                    model.addAttribute("error", "헤드 정보가 없습니다.");
//                }
//            } else {
//                model.addAttribute("error", "응답이 null입니다.");
//            }
//        } catch (Exception e) {
//            log.error("Error occurred while fetching data: ", e); // 오류 로그 출력
//            model.addAttribute("error", "오류가 발생했습니다: " + e.getMessage());
//        }
//
//        return "dogmap/dogmap"; // Thymeleaf 템플릿 파일 이름
//    }
//}


