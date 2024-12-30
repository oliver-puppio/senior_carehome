<template>
  <div class="app-container">
    <el-tabs type="card">
      <el-tab-pane label="院内房间">
        <room-in></room-in>
      </el-tab-pane>
      <el-tab-pane label="社区房间">
        <room-out></room-out>
      </el-tab-pane>
    </el-tabs>
  </div>
</template>
<script>
import RoomIn from "@/views/rms/RoomIn";
import RoomOut from "@/views/rms/RoomOut";

export default {
  name: "roomList",
  components: { RoomIn, RoomOut },
  data() {
    return {

    };
  },
  created() {},

  methods: {

  },
};
</script>
<style scope>

</style>


