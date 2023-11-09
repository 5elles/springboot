document.addEventListener("DOMContentLoaded", () => {
  const countryName = document.querySelector("#countryName")
  const regionName = document.querySelector("#regionName");
  const regionNames = document.querySelectorAll("#regionName option")

  // моя шляпа
  // const settlementTypeName = document.querySelector("#settlementTypeName")
  // const settlementName = document.querySelector("#settlementName")
  // const settlementNames = document.querySelectorAll("#settlementName option")


  const handleCountryNames = (event) => {
    const selectedCountry = Array.from(countryName).find((it) => it.selected);
    regionName.value = undefined;

    regionNames.forEach((it) => {
      it.style.display = it.id === selectedCountry.id ? "block" : "none";
    });
  }

  // //моя шляпа
  // const handleRegionNames = (event) => {
  //   const selectedRegion = Array.from(regionName).find((it) => it.selected);
  //   regionName.value = undefined;
  //
  //   settlementNames.forEach((it) => {
  //     it.style.display = it.id === selectedRegion.id ? "block" : "none";
  //   });
  // }
  // settlementName.addEventListener("change", handleRegionNames)

  countryName.addEventListener("change", handleCountryNames)
})

// document.addEventListener("DOMContentLoaded", () => {
//   const regionName = document.querySelector("#regionName");
//   const settlementTypeName = document.querySelector("#settlementTypeName")
//   const settlementName = document.querySelector("#settlementName")
//   const settlementNames = document.querySelectorAll("#settlementName option")
//
//   const handleRegionNames = (event) => {
//     const selectedRegion = Array.from(regionName).find((it) => it.selected);
//     regionName.value = undefined;
//
//     settlementNames.forEach((it) => {
//       it.style.display = it.id === selectedRegion.id ? "block" : "none";
//     });
//   }
//
//   settlementName.addEventListener("change", handleRegionNames)
// })