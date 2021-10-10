
# terraform {
#   required_providers {
#     google = {
#       source  = "hashicorp/google"
#       version = "3.5.0"
#     }
#   }
# }
# terraform {
#   required_providers {
#     google = {
#       source = "hashicorp/google"
#       version = "3.5.0"
#     }
#   }
#   required_version = ">= 0.13"
# }
# provider "google" {
#   credentials = file("terrform-demo-20211007-c1a849e77e47.json")

#   project = "terrform-demo-20211007"
#   region  = "us-central1"
#   # region  = "us-central1"
#   # zone    = "us-central1-c"
# }

# Create virtual machine
# Create virtual machine
resource "google_compute_instance" "default" {
  project = "terrform-demo-20211007"
  name         = "terraform-instance"
  machine_type = "n1-standard-1"
  zone         = "us-central1-a"

  boot_disk {
    initialize_params {
      image = "debian-cloud/debian-9"
    }
  }
  metadata_startup_script = "sudo apt-get update; sudo apt-get install -yq build-essential python-pip rsync; pip install flask"

  network_interface {
    network = "default"
    access_config {
    }
  }
}

# resource "google_compute_instance" "default" {
#   name         = "terraform-instance"
#   machine_type = "n1-standard-2"
#   zone         = "us-west1-a"

#   boot_disk {
#     initialize_params {
#       image = "debian-cloud/debian-9"
#     }
#   }

#   network_interface {
#     network = "default"
#     access_config {
#     }
#   }
# }

# module "project_services" {
#   source  = "terraform-google-modules/project-factory/google//modules/project_services"
#   version = "3.3.0"

#   project_id = "terrform-demo-20211007"

#   activate_apis = [
#     "compute.googleapis.com",
#     "oslogin.googleapis.com"
#   ]

#   disable_services_on_destroy = false
#   disable_dependent_services  = false
# }


# resource "google_compute_network" "vpc_network" {
#   name = "terraform-network"
# }


# terraform {
#   required_providers {
#     google = {
#       source = "hashicorp/google"
#       version = "3.5.0"
#     }
#   }
#   required_version = ">= 0.13"
# }

# provider "google" {
#   credentials = "${file("terrform-demo-20211007-3cf0e80c18c4.json")}"
#   project = "terrform-demo-20211007"
#   region = "us-central1"
# }

# # provider "google" {
# #   credentials = "${file("terrform-demo-20211007-3cf0e80c18c4.json")}"
# #   project = "terrform-demo-20211007"
# #   region = "us-central1"
# #   zone   = "us-central1-a"
# # }
# resource "random_id" "instance_id" {
#  byte_length = 8
# }

# resource "google_compute_instance" "default" {
#   name         = "test-instance-v1"
#   machine_type = "n1-standard-1"
#   zone         = "us-central1-a"

#   tags = ["foo", "bar"]

#   boot_disk {
#     initialize_params {
#       image = "debian-cloud/debian-9"
#     }
#   }

#   // Local SSD disk
#   scratch_disk {
#     interface = "SCSI"
#   }

#   network_interface {
#     network = "default"

#     access_config {
#       // Ephemeral IP
#     }
#   }

#   # metadata = {
#   #   foo = "bar"
#   # }

#   # metadata_startup_script = "echo hi > /test.txt"

#   # service_account {
#   #   scopes = ["userinfo-email", "compute-ro", "storage-ro"]
#   # }
# }

# resource "google_compute_instance" "vm_instance" {
#     name = "vm-host-${random_id.instance_id.hex}"
#     machine_type = "f1-micro"
#     boot_disk {
#       initialize_params {
#       image = "debian-cloud/debian-9"
#     }
#     }
#     network_interface {
#       network= "default"
#     }
# }
