# jhttp-builder

## Introduction
jhttp-builder is a java library, it makes easy to create a request

## Usage

```java
class DemoRequest {
    @HttpHeader(name = "id")
    private String id;

    @HttpHeader(name = "name")
    private String name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }
}

public class App {
    public static void main(String[] args){
        CallOptional optional = new CallOptional((body, classType) -> body);
        Configuration cfg = new Configuration(new OkHttpClient(), "https://http-builder.free.beeceptor.com");
        cfg.setAuth(new HttpBasicAuth("abc", "12345"));
        DemoRequest request = new DemoRequest();
        request.setId("12345");
        request.setName("phucch");
        try {
            ApiResponse resp = HttpBuilder.newBuilder(cfg)
                    .setUri("/api")
                    .build(request)
                    .call(String.class, optional);
            System.out.println("resp " + resp.getHeaders());
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
```