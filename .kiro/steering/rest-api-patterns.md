---
inclusion: manual
---

# REST API Quick Reference

## HTTP Methods

- GET → read data
- POST → create new resource (returns 201)
- PUT → update existing resource
- DELETE → remove resource (returns 204)

## Common Spring Annotations

```java
@RestController           // handles HTTP, returns JSON
@RequestMapping("/path")  // base URL
@GetMapping               // GET handler
@PostMapping              // POST handler
@PutMapping("/{id}")      // PUT handler
@DeleteMapping("/{id}")   // DELETE handler
@PathVariable             // value from URL
@RequestBody              // JSON body → Java object
```

## ResponseEntity Examples

```java
ResponseEntity.ok(lesson);                          // 200
ResponseEntity.status(HttpStatus.CREATED).body(x);  // 201
ResponseEntity.noContent().build();                  // 204
ResponseEntity.notFound().build();                   // 404
```
