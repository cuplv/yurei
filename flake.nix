{
  description = "yurei flake";
  inputs = {
    nixpkgs.url = "https://flakehub.com/f/NixOS/nixpkgs/0.2305.*.tar.gz";
    flake-schemas.url = "https://flakehub.com/f/DeterminateSystems/flake-schemas/*.tar.gz";
    typelevel-nix.url = "github:typelevel/typelevel-nix";
    nixpkgs.follows = "typelevel-nix/nixpkgs";
    flake-utils.follows = "typelevel-nix/flake-utils";
  };
  outputs = { self, nixpkgs, flake-schemas, flake-utils, typelevel-nix }: 
    let
      # Helpers for producing system-specific outputs
      supportedSystems = [ "x86_64-linux" ];
      forEachSupportedSystem = f: nixpkgs.lib.genAttrs supportedSystems (system: f {
        pkgs = import nixpkgs { 
          inherit system;
          overlays = [ typelevel-nix.overlay ];
        };
      });
    in {
      # Schemas tell Nix about the structure of your flake's outputs
      schemas = flake-schemas.schemas;

      # Development environments
      devShells = forEachSupportedSystem ({ pkgs }: {
        default = pkgs.mkShell {
          # Pinned packages available in the environment
          imports = [typelevel-nix.typelevelShell];
          packages = with pkgs; [
            curl
            git
            wget
            nixpkgs-fmt
            # LSP Support
            nodejs
          ];
        };
      });
    };
}
