# Git XTension

Gxt (Git XTension) is a command-line tool that adds convenient actions to commonly used Git commands.

## Features

-  Override both author and committer dates for commits.

## Getting started

This guide shows how to obtain a copy of the project and get the tool running on your local machine.

### System requirements

This project depends on the command-line tools below being available in your environment:

-  `java`
-  `git`
-  `date` from GNU coreutils

### Downloading the codebase

[Fork this repo][1] on GitHub and clone your fork. Alternatively, you can [download this repo][2] as a zip file and extract it.

### Running the tool

> **Note:** Use Git Bash to run commands on Windows.

1. Navigate into the project's root directory.
2. Install the project as a distribution:

   ```shell
   ./gradlew installDist
   ```

3. Run the tool from the build directory:

   ```shell
   ./build/install/gxt/bin/gxt --help
   ```

Alternatively, you can open and run the project in [IntelliJ IDEA][3]. For more on how to run the tool with arguments, see [here][4].

## Author

Mayeku Khisa - _Maintainer_ - [@mayekukhisa][5].

## License

Gxt is available under the [MIT License][6]. Copyright &copy; 2022, Mayeku Khisa.

[1]: https://docs.github.com/en/get-started/quickstart/fork-a-repo
[2]: https://github.com/mayekukhisa/gxt/archive/refs/heads/main.zip
[3]: https://www.jetbrains.com/idea/
[4]: https://www.jetbrains.com/help/idea/running-applications.html
[5]: https://github.com/mayekukhisa
[6]: LICENSE
