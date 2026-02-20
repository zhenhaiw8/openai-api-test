# Spring Boot 本地接口调用 OpenAI 示例

这是一个最小可运行的 Java Spring Boot 项目，提供一个本地 HTTP 接口：

- `POST /api/ask`

调用后会把问题转发到 OpenAI Responses API，并返回回答。

## 1. 环境要求

- JDK 17+
- Maven 3.9+
- OpenAI API Key

## 2. 配置 API Key

在启动前配置环境变量：

```bash
export OPENAI_API_KEY="你的key"
```

## 3. 启动项目

```bash
mvn spring-boot:run
```

## 4. 调用本地接口

```bash
curl -X POST http://localhost:8080/api/ask \
  -H "Content-Type: application/json" \
  -d '{"question":"帮我写一段关于Spring Boot的简介"}'
```

返回示例：

```json
{
  "answer": "Spring Boot 是一个用于快速构建 Spring 应用的框架..."
}
```

## 5. 可配置项

在 `src/main/resources/application.yml` 中：

- `openai.api.url`：默认 `https://api.openai.com/v1/responses`
- `openai.model`：默认 `gpt-4.1-mini`

