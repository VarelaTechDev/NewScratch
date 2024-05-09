```import os

# List of parent directories to look for
parent_folders = ["api", "client", "business"]
# Versions to replace
versions_to_replace = {
    "0.1.0-SNAPSHOT": "0.1.0-PERSONAL"
}

def replace_version_in_file(file_path):
    """Replace specified versions in the build.gradle file."""
    with open(file_path, 'r') as file:
        contents = file.readlines()
    
    changed = False
    with open(file_path, 'w') as file:
        for line in contents:
            for old_version, new_version in versions_to_replace.items():
                if f"version \"{old_version}\"" in line or f"version '{old_version}'" in line:
                    line = line.replace(old_version, new_version)
                    changed = True
            file.write(line)
    
    if changed:
        print(f"Updated version in {file_path}")

def search_and_replace_version(root_dir):
    """Search directories that match the parent_folders and replace version in build.gradle."""
    for parent in parent_folders:
        # Path pattern to match
        parent_pattern = parent + "-"
        for root, dirs, files in os.walk(root_dir):
            # Check if current folder starts with one of the parent_patterns
            for dir_name in dirs:
                if dir_name.startswith(parent_pattern):
                    build_gradle_path = os.path.join(root, dir_name, 'build.gradle')
                    if os.path.isfile(build_gradle_path):
                        replace_version_in_file(build_gradle_path)

if __name__ == "__main__":
    current_directory = os.getcwd()
    search_and_replace_version(current_directory)
```

# Java Spring Boot Implementation of WebAuthn

This example app demonstrates how WebAuthn works using Spring Boot.

Please read [Building a WebAuthn Application with Java][blog-post] to see how this app was created.

**Prerequisites:**

**Java 17**: This project uses Java 17. If you don't have Java 17, you can install OpenJDK. Instructions are found on the [OpenJDK website](https://openjdk.java.net/install/).

* [Getting Started](#getting-started)
* [Start the Apps](#start-the-apps)
* [Links](#links)
* [Help](#help)
* [License](#license)

## Getting Started

To install this example application, run the following commands:

```bash
git clone https://github.com/oktadev/webauthn-java-example.git
cd webauthn-java-example
```

This will get a copy of the project installed locally.

## Start the Apps

To install all of its dependencies and the app, run:

```bash
./mvnw spring-boot:run
```

You can now test the application by opening http://localhost:8080

## Links

This example uses the following open source libraries:

* [Spring Boot](https://spring.io/projects/spring-boot)
* [Yubico's Server-side Web Authentication Library](https://developers.yubico.com/java-webauthn-server/)

## Help

Please post any questions as comments on the [blog post], or visit our [Okta Developer Forums](https://devforum.okta.com/).

## License

Apache 2.0, see [LICENSE](LICENSE).

[blog-post]: https://developer.okta.com/blog/2022/04/26/webauthn-java
