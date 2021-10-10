terraform {
  required_providers {
    google = {
      source = "hashicorp/google"
      version = "3.5.0"
    }
  }
}

provider "google" {
  credentials = file("ivory-bit-323815-dc403ef30acb.json")
  project = "ivory-bit-323815"
#   region = "us-central1"
  
}
resource "random_id" "instance_id" {
 byte_length = 8
}


resource "google_compute_instance" "vm_instance" {
    name = "vm-host-${random_id.instance_id.hex}"
    machine_type = "n1-standard-2"
    zone   = "us-west1-a"
    boot_disk {
      initialize_params {
        image = "debian-cloud/debian-9"
    }
    }
    network_interface {
      network= "default"
    }
}
