#BookStore

# 开发环境

jdk1.8+tomcat8+springmvc4+mybatis3 

## java代码格式规范
- **参考[阿里java代码规范](https://github.com/xuguanhua/xmtgzs/blob/master/%E9%98%BF%E9%87%8C%E5%B7%B4%E5%B7%B4%2BJava%2B%E5%BC%80%E5%8F%91%E6%89%8B%E5%86%8C.pdf)**
## Pull Request要求

- **代码格式** 提交前请按项目风格进行格式化。

- **必须添加测试！** - 提交前必须确认代码可运行。

- **记得更新文档** - 保证`README.md`以及其他相关文档及时更新，和代码的变更保持一致性。

- **创建feature分支** - 最好不要从你的master分支提交 pull request。

- **一个feature提交一个pull请求** - 如果你的代码变更了多个操作，那就提交多个pull请求吧。

- **清晰的commit历史** - 保证你的pull请求的每次commit操作都是有意义的。如果你开发中需要执行多次的即时commit操作，那么请把它们放到一起再提交pull请求。**详情请了解git rebase**

## Commit message 规范

规定格式如下：

```
<type>(<scope>): <subject> #issue_number

<description>
```

其中，type、scope、subject是必需的，description 可以省略。不管是哪一个部分，任何一行都不得超过72个字符（或100个字符）。这是为了避免自动换行影响美观。

#### type 部分取值说明

type用于说明 commit 的类别，只允许使用下面7个标识。

- **feature:** 新功能（feature）
- **fix:** 修补bug、style等
- **docs:** 文档（documentation）
- **refactor:** 重构（即不是新增功能，也不是修改bug的代码变动）
- **test:** 增加测试
- **chore:** 构建过程或辅助工具的变动

#### scope 部分取值说明

一般为项目功能模块、组件的名字，用来描述本次 commit 影响的范围，比如 [node commits](https://github.com/nodejs/node/commits/master) 、[go commits](https://github.com/golang/go/commits/master) 。嵌套层级结构可以用 / 表示，如 net/http。影响多个模块、组件可以用 , 隔开（不加空格），如 net/http,cmd。后加入项目的新成员应遵循已有的 scope 约定（通过 git log 可以查看某个文件的提交历史），不要自己编造。使用首字母小写的驼峰命名。除具体的模块、组件名之外，可以使用 base 表示基础结构、框架相关的改动，用 misc 表示杂项改动，用 all 表示大范围重构。

#### subject 部分

subject是 commit 目的的简短描述，50 个字符左右的简要说明，首字母小写，通常是动宾结构，描述做了什么事情，动词用一般现在时，禁止出现 update code ， fix bug 等无实际意义的描述，好的例子： select connector by sorting free memory （不需要形如 update about how to select connector ... 的啰嗦写法）, fix success tip can not show on IE8 （不需要形如 fix bug of ... 的啰嗦写法）

- 以动词开头，使用第一人称现在时，比如change，而不是changed或changes
- 尽量使用简单句保证简洁性
- 第一个字母小写
- 结尾不加句号（.）
- 通过翻译检测工具确认英文的正确性和可读性

#### Commit message 延伸阅读

- [Conventional Changelog](https://github.com/ajoslin/conventional-changelog)
- [Commit message 和 Change log 编写指南](http://www.ruanyifeng.com/blog/2016/01/commit_message_change_log.html)

### 实战：项目代码分支管理和合并

#### 1. 克隆远端的develop分支

```bash
git clone -b develop <remote-repository-name>
```

#### 2. 创建新的分支完成 issue

开始实现一个feature或者解决一个bug之前，
首先切换到develop分支上

```bash
git checkout develop
```

**然后创建feature或者bug的分支,并且切换到该分支***
**然后创建feature或者bug的分支,并且切换到该分支***
**然后创建feature或者bug的分支,并且切换到该分支***
（重要的事情说三遍）
指令如下

```bash
git checkout -b <feature/bug-branch-name>
```

**分支名称需要遵循命名规范:**

- **feature:** 格式遵守`feat-issueID-moduleName`， 例如`feature-4213-sns`
- **bug:** 格式遵守`fix-issueID-moduleName`， 例如`fix-4216-user`
- **docs:** 格式遵守`docs-document-description`， 例如`docs-deploy-process`
- **refactor:** 格式遵守`refactor-moduleName-description`， 例如`refactor-user-rpc`
- **chore:** 格式遵守`chore-build-task`， 例如`chore-grunt-csssprite`
- **test:** 格式遵守`test-moduleName-description`， 例如`test-bigdata-merge-users`

#### 3. 完成开发的工作

修改一些文件，添加修改的文件到暂存区(staging area)

```bash
git add .
```

将修改后的文件提交到本地的版本库中

```bash
git commit -am 'Add a new feature'
```

你可以在本地多次提交，可以任意写你每个commit的信息(最好也同时遵守[规范](https://github.com/ajoslin/conventional-changelog/blob/master/conventions/angular.md))

注意：

- **如果一个开发任务会持续多天，建议每天都进行两次 rebase develop 分支的操作**：
  - `git checkout develop`
  - `git pull`
  - `git checkout <feature/bug-branch-name>`
  - `git rebase develop`
- 关于如何减少冲突，参见 [Avoid 80% of Git merge conflicts](https://medium.com/front-end-hacking/avoid-80-of-merge-conflicts-with-git-rebase-b5d755a082a6)

#### 4. 独立分支上合并提交

这里 **特别注意** ，使用rebase命令将你在自己分支上的多次提交合并成一次提交，同时合并develop分支

```bash
git checkout <feature／bug-branch-name>
git rebase -i develop
```

**Notice:** 使用rebase命令将你在feature／bug分支上的多次提交合并成一次， 保证提交的原子性。其中`-i` 参数会提供交互的方式引导，一般你会看到这样的编辑界面

```bash
pick ff76694 feat(sns): add static page for sns module
pick 8e49687 feat(sns): finish the frontend controller for sns module
pick 69f188e feat(sns): finish the backend controller for sns module
# Rebase ff76694..69f188e onto d879700
#
# Commands:
#  p, pick = use commit
#  r, reword = use commit, but edit the commit message
#  e, edit = use commit, but stop for amending
#  s, squash = use commit, but meld into previous commit
#  f, fixup = like "squash", but discard this commit's log message
#  x, exec = run command (the rest of the line) using shell
#
# These lines can be re-ordered; they are executed from top to bottom.
#
# If you remove a line here THAT COMMIT WILL BE LOST.
#
# However, if you remove everything, the rebase will be aborted.
#
# Note that empty commits are commented out
```

下面的注释中Command有详细的解释，一般将你要合并的多个提交前面的pick改为squash（如果有多个commit除了第一个是pick其他都是squash），对于我们的示例，修改为

```bash
pick ff76694 feat(sns): add static page for sns module
pick 8e49687 feat(sns): finish the frontend controller for sns module
squash 69f188e feat(sns): finish the backend controller for sns module
# Rebase ff76694..69f188e onto d879700
#...
```

保存文件退出编辑，正常情况下这样就完事了，但是如果develop分支上有新的提交和你的工作分支提交发生冲突，就会看到下面的情况

```bash
error: could not apply ff76694... feat(sns): finish the backend controller for sns module

When you have resolved this problem, run "git rebase --continue".
If you prefer to skip this patch, run "git rebase --skip" instead.
To check out the original branch and stop rebasing, run "git rebase --abort".
Could not apply ff76694624019140e05cd9d443aa547e62c5c24b... add line 3
```

编辑冲突文件（冲突文件中可能不会列出你在当前分支上所有的改动，只会标出冲突部分），选择需要的部分，保存文件

```bash
git add <modified files>
git rebase --continue
```

之后你会看到类似这样的提示

```bash
feat(sns): finish the backend controller for sns module

# Please enter the commit message for your changes. Lines starting
# with '#' will be ignored, and an empty message aborts the commit.
# rebase in progress; onto d879706
# You are currently rebasing branch 'feature' on 'd879706'.
#
# Changes to be committed:
#       modified:   test.txt
#
```

这里是让你编辑你第一次提交的comment，更改comment内容后保存退出，紧接着会提示你编辑合并之后commit的comment，你在上一次添加的comment会合并进来，你看到的会是这样的

```bash
# This is a combination of 2 commits.
# The first commit's message is:

# feat(sns): finish the backend controller for sns module
# -----change to----->

feat(sns): finish the backend controller for sns module

# This is the 2nd commit message:

# feat(sns): finish the backend controller for sns module

# This is the 3nd commit message:

# feat(sns): finish the backend controller for sns module

# Please enter the commit message for your changes. Lines starting
# with '#' will be ignored, and an empty message aborts the commit.
# rebase in progress; onto d879706
# You are currently editing a commit while rebasing branch 'feature' on 'd879706'.
#
# Changes to be committed:
#       modified:   test.txt
#
```

**去掉多余的comment内容(像示例中一样，只保留一条comment)**，保存退出。将工作分支上的多次提交合并成一次提交，更详细的说明看[这里](http://gitready.com/advanced/2009/02/10/squashing-commits-with-rebase.html)

#### ５. 提交分支，发PR合并请求到develop分支

在本地解决合并之后的冲突，同时仅保留feature本身提交的comment(去除在merge中自动生成的提交信息)，解决冲突后将分支提交到中央仓库

```bash
git push origin <feature／bug-branch-name>
```

在github上手动创建一个新的pull request，指定source branch和target branch。
