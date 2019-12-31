#### web3j cli安装(centos)
1. install
```
curl -L https://get.web3j.io | sh
```
2. 老规矩，网站连接失败，只能自己从github下载压缩包手动安装
```
https://github.com/web3j/web3j/releases
```
3. 创建目录
```
mkdir -p "$HOME/.web3j"
```
4. 将下载的`web3j-4.5.4.tar`和脚本`web3j-cli-install.sh`放到`/root/.web3j/`，执行脚本
```
sudo bash web3j-cli-install.sh
```
指令`curl -L https://get.web3j.io | sh`里面有安装脚本，但是由于访问权限问题导致失败。`web3j-cli-install.sh`脚本是从官网改编而来，移除了下载部分。如果下载的版本号有变更，脚本里的版本号也要对应修改。
5. 环境变量
```
export PATH=$PATH:/root/.web3j
```
6. 验证安装
```
web3j version
```


**web3j-cli-install.sh**
```
#!/bin/sh

install_web3j() {
  echo "Installing Web3j..."
  tar -xf "$HOME/.web3j/web3j-4.5.4.tar" -C "$HOME/.web3j"
  echo "export PATH=\$PATH:$HOME/.web3j" >"$HOME/.web3j/source.sh"
  chmod +x "$HOME/.web3j/source.sh"
}

source_web3j() {
  SOURCE_WEB3J="\n[ -s \"$HOME/.web3j/source.sh\" ] && source \"$HOME/.web3j/source.sh\""
  if [ -f "$HOME/.bashrc" ]; then
    bash_rc="$HOME/.bashrc"
    touch "${bash_rc}"
    if ! grep -qc '.web3j/source.sh' "${bash_rc}"; then
      echo "Adding source string to ${bash_rc}"
      printf "${SOURCE_WEB3J}\n" >>"${bash_rc}"
    else
      echo "Skipped update of ${bash_rc} (source string already present)"
    fi
  fi
  if [ -f "$HOME/.bash_profile" ]; then
    bash_profile="${HOME}/.bash_profile"
    touch "${bash_profile}"
    if ! grep -qc '.web3j/source.sh' "${bash_profile}"; then
      echo "Adding source string to ${bash_profile}"
      printf "${SOURCE_WEB3J}\n" >>"${bash_profile}"
    else
      echo "Skipped update of ${bash_profile} (source string already present)"
    fi
  fi
  if [ -f "$HOME/.bash_login" ]; then
    bash_login="$HOME/.bash_login"
    touch "${bash_login}"
    if ! grep -qc '.web3j/source.sh' "${bash_login}"; then
      echo "Adding source string to ${bash_login}"
      printf "${SOURCE_WEB3J}\n" >>"${bash_login}"
    else
      echo "Skipped update of ${bash_login} (source string already present)"
    fi
  fi
  if [ -f "$HOME/.profile" ]; then
    profile="$HOME/.profile"
    touch "${profile}"
    if ! grep -qc '.web3j/source.sh' "${profile}"; then
      echo "Adding source string to ${profile}"
      printf "$SOURCE_WEB3J\n" >>"${profile}"
    else
      echo "Skipped update of ${profile} (source string already present)"
    fi
  fi

  if [ -f "$(command -v zsh 2>/dev/null)" ]; then
    file="$HOME/.zshrc"
    touch "${file}"
    if ! grep -qc '.web3j/source.sh' "${file}"; then
      echo "Adding source string to ${file}"
      printf "$SOURCE_WEB3J\n" >>"${file}"
    else
      echo "Skipped update of ${file} (source string already present)"
    fi
  fi
}

completed() {
  ln -sf "$HOME/.web3j/web3j-4.5.4/bin/web3j" $HOME/.web3j/web3j
  printf '\n'
  printf "$GREEN"
  echo "Web3j was succesfully installed."
  echo "To use web3j in your current shell run:"
  echo "source \$HOME/.web3j/source.sh"
  echo "When you open a new shell this will be performed automatically."
  echo "To see what web3j's CLI can do you can check the documentation bellow."
  echo "https://docs.web3j.io/command_line_tools/ "
  printf "$RESET"
  exit 0
}

main() {
  install_web3j
  source_web3j
  completed
}

main
```
