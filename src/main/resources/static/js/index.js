document.addEventListener("DOMContentLoaded", () => {
  const countryName = document.querySelector("#countryName")
  const regionName = document.querySelector("#regionName");
  const regionNames = document.querySelectorAll("#regionName option")

  const handleCountryNames = (event) => {
    const selectedCountry = Array.from(countryName).find((it) => it.selected);
    regionName.value = undefined;

    regionNames.forEach((it) => {
      it.style.display = it.id === selectedCountry.id ? "block" : "none";
    });
  }

  countryName.addEventListener("change", handleCountryNames)
})

