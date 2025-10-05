Few Reasons behind Design Decision 

1 - Used @NotBlank instead of @NotNull and @NotEmpty due to the fact it checks not null
    and also trim before checking the length greater than zero.

2 - Noarg constructor is not used for business logic need. purely because JPA specification
    says it requires it