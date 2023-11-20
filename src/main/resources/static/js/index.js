document.addEventListener("DOMContentLoaded", () => {
  const countryName = document.querySelector("#countryName");
  const regionName = document.querySelector("#regionName");
  const settlementName = document.querySelector("#settlementName");

  const regionNames = regionName.querySelectorAll("#regionName option");
  const settlementNames = settlementName.querySelectorAll("#settlementName option");

  let prevCountry = "";

  const handleChangeCountry = (event) => {
    const { value } = event.target;

    if (value === "") {
      regionName.setAttribute("disabled", true);
      settlementName.setAttribute("disabled", true);
    } else if (value !== "" && value !== prevCountry) {
      settlementName.setAttribute("disabled", true);
      regionName.removeAttribute("disabled");
    } else {
      regionName.removeAttribute("disabled");
    }

    prevCountry = value;
    regionName.value = "";
    settlementName.value = "";

    regionNames.forEach((it) => {
      it.style.display = it.id === value ? "block" : "none";
    });
  }

  const handleChangeRegion = (event) => {
    const { value } = event.target;

    if (value === "") {
      settlementName.setAttribute("disabled", true)
    } else {
      settlementName.removeAttribute("disabled");
    }

    settlementName.value = "";

    settlementNames.forEach((it) => {
      it.style.display = it.id === value ? "block" : "none";
    });
  }

  countryName.addEventListener("change", handleChangeCountry);
  regionName.addEventListener("change", handleChangeRegion);
})

