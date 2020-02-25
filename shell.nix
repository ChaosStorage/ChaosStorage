{ pkgs ? import <nixpkgs> {
  overlays = [
    (super: self: {
      jdk = super.jdk12;
    })
  ];
}, ... }:

with pkgs;

stdenv.mkDerivation {
  name = "shell";
  buildInputs = [
    gradle
    jdk
  ];
}
