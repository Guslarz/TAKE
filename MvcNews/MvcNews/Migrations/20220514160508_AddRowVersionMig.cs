using System;
using Microsoft.EntityFrameworkCore.Migrations;

#nullable disable

namespace MvcNews.Migrations
{
    public partial class AddRowVersionMig : Migration
    {
        protected override void Up(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.AddColumn<byte[]>(
                name: "RowVersion",
                table: "News",
                type: "rowversion",
                rowVersion: true,
                nullable: true);
        }

        protected override void Down(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.DropColumn(
                name: "RowVersion",
                table: "News");
        }
    }
}
