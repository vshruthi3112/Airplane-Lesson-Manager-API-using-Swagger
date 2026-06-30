---
inclusion: manual
---

# Dependency Injection

Spring creates objects and wires them together automatically.

## How it works

1. Annotate classes: `@Controller`, `@Service`, `@Repository`
2. Spring creates one instance of each at startup
3. Dependencies are passed through constructors

## Example

```java
@Service
public class LessonService {
    private final LessonRepository repo;

    public LessonService(LessonRepository repo) {
        this.repo = repo;  // Spring provides this
    }
}
```

## Why constructor injection?

- Dependencies are clear and required
- Fields can be `final` (immutable)
- Easy to test (just pass mocks)
